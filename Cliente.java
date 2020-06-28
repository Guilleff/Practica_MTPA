
package piedrapapeltijera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JFrame;

public class Cliente extends JFrame implements ActionListener,Runnable{
    
    private Socket sckCliente;
    private InputStream flujoLectura;
    private OutputStream flujoEscritura;
    private String usuario;
    private String contraseña;
    
    
    public Cliente(Socket sck,String user,String passw){
        try{
        this.sckCliente=sck;
        this.flujoLectura=this.sckCliente.getInputStream();
        this.flujoEscritura=this.sckCliente.getOutputStream();
        this.usuario=user;
        this.contraseña=passw;
        //inicializar JFrame con sus componentes
        }catch(IOException ex){
            
        }
    }
    
    @Override
    public void run(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
    }
       
}
