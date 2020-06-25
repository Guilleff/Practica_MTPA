
package piedrapapeltijera;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    public static void main (String args[]) throws Exception
  {
    
    
    ServerSocket srv = new ServerSocket(9998);
    System.out.println("Servidor Arrancado");
    do{
        Socket sck =  srv.accept();  
        System.out.println("Alguien conectado...");
        InputStream is = sck.getInputStream();
        byte[] buffer = new byte[8];
        int nb; //Cuantos bytes he leido
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        do{

            nb = is.read(buffer); //suma@389@556
            baos.write(buffer, 0, nb);


        }while (nb > 0 && is.available() > 0);
        
        Mensaje msg=Mensaje.CrearMensaje(buffer);
        
        Llamar_accion(msg);
        
        /*
        String loquemeLlega = new String(baos.toByteArray());
        System.out.println(loquemeLlega);*/
    }while (true);
    
    
    
  }
  
  public static void Llamar_accion(Mensaje msg){
      //implementar logica del server
  }
}
