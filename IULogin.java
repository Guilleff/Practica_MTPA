
package piedrapapeltijera;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Clase IULogin muestra la pantalla en la que se registran los usuarios o se inicia
 * sesion
 * @author david
 * @version 1.0
 */

public class IULogin extends JFrame implements ActionListener,Runnable{

    /**
     * Definimos los objetos que vamos a usar en la clase
     */
    
    private JButton AccesoBoton;
    private JButton RegistroBoton;
    private JTextField usuarioText;
    private JTextField contraseñaText;
    private JLabel usuarioLabel;
    private JLabel contraseñaLabel;
    
    private Socket cliente;
    private InputStream flujoLectura;
    private OutputStream flujoEscritura;
    
    private String accion;
    
    
   
    public static void main(String args[]){
        IULogin prueba= new IULogin();
    }
    
    
    public IULogin(){
        initComponents();
        
    }
    
    /**
     * Metodo que inicia la pantalla para que se inicie sesion o se registre un
     * usuario
     */
    
    public void initComponents(){
        
        this.setTitle("IULogin");
        this.setSize(500, 250);
        this.setLayout(null);
        AccesoBoton = new JButton("Iniciar Sesion");
        RegistroBoton = new JButton("Registrarse");
        usuarioLabel = new JLabel("Usuario");
        contraseñaLabel = new JLabel("Contraseña");
        usuarioText = new JTextField(20);
        contraseñaText = new JTextField(20);
        
       
        AccesoBoton.addActionListener(this);
        RegistroBoton.addActionListener(this);
       
        add(usuarioLabel);
        usuarioLabel.setBounds(50,40,50,25);
        add(usuarioText);
        usuarioText.setBounds(200,40,150,25);
        add(contraseñaLabel);
        contraseñaLabel.setBounds(50,80,80,25);
        add(contraseñaText);
        contraseñaText.setBounds(200,80,150,25);
        add(AccesoBoton);
        AccesoBoton.setBounds(150,130,125,50);
        add(RegistroBoton);
        RegistroBoton.setBounds(300,130,110,50);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
    /**
     * Metodo que inicia la comunicacion con el servidor
     */
    
    public void initComunication(){
        try{
            cliente = new Socket("127.0.0.1", 9998);  
            this.flujoLectura = cliente.getInputStream();
            this.flujoEscritura = cliente.getOutputStream();
            Thread hilo = new Thread(this);
            hilo.start();
        }
        catch(IOException ex)
        {
            System.out.println("No se pudo conectar con el servidor");
        }
    }
    @Override
    public void run(){
       try {
            this.flujoEscritura.write(toString(usuarioText.getText(),contraseñaText.getText(),accion).getBytes("UTF-8"));
                byte[] buffer = new byte[16];
                int nb; 
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                do{
                    nb = flujoLectura.read(buffer); 
                    baos.write(buffer, 0, nb);
                }while (nb > 0 && flujoLectura.available() > 0);
                Mensaje msg=Mensaje.CrearMensaje(baos.toByteArray());
                LlamarAccionLogin(msg);
       } catch (IOException ex) {
           System.out.println("No se pudo enviar el mensaje");
       }
    }
    
    /**
     * Metodo que indica la accion que se realiza al inicio o registro
     * @param e 
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == AccesoBoton){
            if(CamposVacios(usuarioText.getText(),contraseñaText.getText())==true){
                JOptionPane.showMessageDialog(this, "Campos no completados", "Error", JOptionPane.INFORMATION_MESSAGE, null);
            }
            else{
                accion="Acceso";
                initComunication();
            }
        }
        else if(e.getSource() == RegistroBoton){
            if(CamposVacios(usuarioText.getText(),contraseñaText.getText())==true){
                JOptionPane.showMessageDialog(this, "Campos no completados", "Error", JOptionPane.INFORMATION_MESSAGE, null);
            }
            else{
                accion="Registro";
                initComunication();
            }
        }
    }

    public boolean CamposVacios(String user,String passw){
        if("".equals(user)||"".equals(passw)){
           return true;
        }
        else{
            return false;
        }
    }
    
    
    public String toString(String user, String passw, String accion){
        return accion+"@"+user+"@"+passw;
    }
    
    public void LlamarAccionLogin(Mensaje msg){
        switch(msg.getAccion()){
            case "Aceptado":
                dispose();
                break;
            case "Denegado":
                try {
                    cliente.close();
                } catch (IOException ex) {
                    System.out.println("No se pudo cerrar el socket");
                }
                System.out.println("Usuario o contraseña no validas");
                JOptionPane.showMessageDialog(this, "Usuario o contraseña no validas", "Error", JOptionPane.INFORMATION_MESSAGE, null);
                break;
            case "DenegadoE":
                try {
                    cliente.close();
                } catch (IOException ex) {
                    System.out.println("No se pudo cerrar el socket");
                }
                JOptionPane.showMessageDialog(this, "Usuario ya existe", "Error", JOptionPane.INFORMATION_MESSAGE, null);
                break;
        }
    }
}
