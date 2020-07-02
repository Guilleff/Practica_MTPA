
package piedrapapeltijera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;


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
    DefaultListModel<String>model=new DefaultListModel<>();
    String[] datos={"hola","adios"};//borrar
    
    
    //solo sirve para comprobar las cosas sin tener que venir desde iulogin--->server--->cliente
    /*public static void main(String args[]){
        Cliente cliente=new Cliente();
    }
    public Cliente(){
        usuario="prueba";
        initComponents();
        Thread hiloCliente = new Thread(this);
        hiloCliente.start();
    }*/
    //hasta aqui
    
    public Cliente(Socket sck,String user,String passw){
        try{
        this.sckCliente=sck;
        this.flujoLectura=this.sckCliente.getInputStream();
        this.flujoEscritura=this.sckCliente.getOutputStream();
        this.usuario=user;
        this.contraseña=passw;
        //inicializar JFrame con sus componentes
        initComponents();
        Thread hiloCliente = new Thread(this);
        hiloCliente.start();
        
        
        
        
        }catch(IOException ex){
            System.out.println("No se pudo conectar con el servidor");
        }
    }
    
    @Override
    public void run(){
        //seguir aqui, poner que cada vez que entre un user actualizar a todos
        
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == RetarAButton){
            
        }
        
    }
       
    public void initComponents(){
        //panel.updateUI() puede que tenga que usar esto
        this.setTitle(getUsuario());
        this.setSize(380,450);
        this.setLayout(null);
        
        RetarA=new JLabel("Indica el jugador que retar");//clica sobre el jugador, esto ponerlo mejor
        RetarAText=new JTextField(20);
        RetarAButton=new JButton("Enviar Reto");
        
        UsuariosInfo=new JLabel("Usuarios conectados");
        
        UsuariosConectados=new JList(model);
        
        RetadoPor=new JLabel("No tienes retos pendientes");//"Has sido retado por:"+usuario
        AceptarReto= new JButton("Aceptar");
        RechazarReto= new JButton("Rechazar");
        
        add(RetarA);
        RetarA.setBounds(15,20,150,30);
        add(RetarAText);
        RetarAText.setBounds(15, 55, 150, 30);
        add(RetarAButton);
        RetarAButton.setBounds(30, 90, 100, 30);
        RetarAButton.addActionListener(this);
        
        add(UsuariosInfo);
        UsuariosInfo.setBounds(212, 20, 150, 30);
        add(UsuariosConectados);
        UsuariosConectados.setBounds(200, 55, 150, 300);
        
        //seguir aqui
        //Esto se vera cuando se le envie un reto, de momento lo dejo aqui
        add(RetadoPor);
        RetadoPor.setBounds(15, 220, 175, 30);
        add(AceptarReto);
        AceptarReto.setBounds(5, 260, 90, 30);
        AceptarReto.setVisible(false);//usar cuando acepte/rechaze el reto
        add(RechazarReto);
        RechazarReto.setBounds(110,260,90,30);
        //hasta aqui
        
        
        this.setVisible(true);
        
        
        addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            try {
                //Hacer lo que yo quiero
                Servidor.EliminarCliente(usuario);
                sckCliente.close();
            } catch (IOException ex) {
                System.out.println("No se pudo cerrar el socket");
            }
        }
        });
        
    }
    
    
    
    public String getUsuario() {
        return usuario;
    }

    public void ActualizarJList(){
        ArrayList<String> otrosUsuarios=new ArrayList<String>();
        otrosUsuarios=Servidor.ListarClientes();
        String[] aux = otrosUsuarios.toArray(new String[otrosUsuarios.size()]);
        model.clear();
        
        for(String user:aux){
            model.addElement(user);
        }
    }
    
    

   

    

    
    
}
