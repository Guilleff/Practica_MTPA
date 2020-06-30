
package piedrapapeltijera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Cliente extends JFrame implements ActionListener,Runnable{
    
    private Socket sckCliente;
    private InputStream flujoLectura;
    private OutputStream flujoEscritura;
    private String usuario;
    private String contraseña;
    
    //estos tres iran en un inicio "escondidos" y cuando se le rete se ven
    private JLabel RetadoPor;
    private JButton AceptarReto;
    private JButton RechazarReto;
    
    private JLabel RetarA;
    private JTextField RetarAText;
    private JButton RetarAButton;
    
    private JLabel UsuariosInfo;
    private JList UsuariosConectados;
    
    
    //solo sirve para comprobar las cosas sin tener que venir desde iulogin--->server--->cliente
    public static void main(String args[]){
        Cliente cliente=new Cliente();
    }
    public Cliente(){
        usuario="prueba";
        initComponents();
    }
    //hasta aqui
    
    public Cliente(Socket sck,String user,String passw){
        try{
        this.sckCliente=sck;
        this.flujoLectura=this.sckCliente.getInputStream();
        this.flujoEscritura=this.sckCliente.getOutputStream();
        this.usuario=user;
        this.contraseña=passw;
        //inicializar JFrame con sus componentes
        //initComponents();
        
        }catch(IOException ex){
            
        }
    }
    
    @Override
    public void run(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
    }
       
    public void initComponents(){
        //panel.updateUI() puede que tenga que usar esto
        this.setTitle(usuario);
        this.setSize(380,450);
        this.setLayout(null);
        RetarA=new JLabel("Indica el jugador que retar");//clica sobre el jugador, esto ponerlo mejor
        RetarAText=new JTextField(20);
        RetarAButton=new JButton("Enviar Reto");
        
        UsuariosInfo=new JLabel("Usuarios conectados");
        UsuariosConectados=new JList();
        
        add(RetarA);
        RetarA.setBounds(15,20,150,30);
        add(RetarAText);
        RetarAText.setBounds(15, 55, 150, 30);
        add(RetarAButton);
        RetarAButton.setBounds(30, 90, 100, 30);
        
        add(UsuariosInfo);
        UsuariosInfo.setBounds(212, 20, 150, 30);
        add(UsuariosConectados);
        UsuariosConectados.setBounds(200, 55, 150, 300);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    
    
    
}
