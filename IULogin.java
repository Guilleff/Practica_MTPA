
package piedrapapeltijera;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class IULogin extends JFrame implements ActionListener,Runnable{

    private JButton AccesoBoton;
    private JButton RegistroBoton;
    private JTextField usuarioText;
    private JTextField contraseñaText;
    private JLabel usuarioLabel;
    private JLabel contraseñaLabel;
    
    private Socket cliente;
    private InputStream flujoLectura;
    private OutputStream flujoEscritura;
    
    
    //esto es solo para hacer pruebas borrar ya que se tiene que crear desde cliente
    public static void main(String args[]){
        IULogin prueba= new IULogin();
    }
    
    
    public IULogin(){
        initComponents();
        
    }
    public void initComponents(){
        /*Definicion de atributos de la ventana*/
        this.setTitle("IULogin");
        this.setSize(500, 250);
        this.setLayout(null);
        AccesoBoton = new JButton("Acceder");
        RegistroBoton = new JButton("Registrarse");
        usuarioLabel = new JLabel("Usuario");
        contraseñaLabel = new JLabel("Contraseña");
        usuarioText = new JTextField(20);
        contraseñaText = new JTextField(20);
        
        /*Action Listeners*/
        AccesoBoton.addActionListener(this);
        RegistroBoton.addActionListener(this);
        /*Inicializacion Panel*/
        add(usuarioLabel);
        usuarioLabel.setBounds(50,40,50,25);
        add(usuarioText);
        usuarioText.setBounds(200,40,150,25);
        add(contraseñaLabel);
        contraseñaLabel.setBounds(50,80,80,25);
        add(contraseñaText);
        contraseñaText.setBounds(200,80,150,25);
        add(AccesoBoton);
        AccesoBoton.setBounds(150,130,100,50);
        add(RegistroBoton);
        RegistroBoton.setBounds(300,130,110,50);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
    public void initComunication(){
        try{
            cliente = new Socket("127.0.0.1", 9998);  
            this.flujoLectura = cliente.getInputStream();
            this.flujoEscritura = cliente.getOutputStream();
            Thread hilo = new Thread(this);
            hilo.start();
        }
        catch(Exception ex)
        {
            System.out.println("No se pudo conectar con el servidor");
        }
    }
    
    @Override
    public void run(){
        try {
            //hay que mandar las dos cosas juntas, ponlo en un mensaje de bytes(nuevo metodo)
            this.flujoEscritura.write(usuarioText.getText().getBytes());
            
        } catch (IOException ex) {
            System.out.println("No se pudo enviar el mensaje");
        }
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == AccesoBoton){
            if(CamposVacios(usuarioText.getText(),contraseñaText.getText())==true){
                JOptionPane.showMessageDialog(this, "Campos no completados", "Error", JOptionPane.INFORMATION_MESSAGE, null);
                
                //dispose(); //cierra la ventan del jframe
            }
            else{
                initComunication();
            }
        }
        else if(e.getSource() == RegistroBoton){
            if(CamposVacios(usuarioText.getText(),contraseñaText.getText())==true){
                JOptionPane.showMessageDialog(this, "Campos no completados", "Error", JOptionPane.INFORMATION_MESSAGE, null);
            }
            else{
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
    
}
