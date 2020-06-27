
package piedrapapeltijera;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Servidor {
    public static void main (String args[]) throws Exception
  {
    
    
    ServerSocket srv = new ServerSocket(9998);
    System.out.println("Servidor Arrancado");
    do{
        Socket sck =  srv.accept();  
        System.out.println("Alguien conectado...");
        InputStream is = sck.getInputStream();
        OutputStream os= sck.getOutputStream();
        byte[] buffer = new byte[16];
        int nb; //Cuantos bytes he leido
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        do{

            nb = is.read(buffer); //suma@389@556
            baos.write(buffer, 0, nb);


        }while (nb > 0 && is.available() > 0);
        System.out.println(buffer);
        Mensaje msg=Mensaje.CrearMensaje(buffer);
        
        
        
        String permiso=Llamar_accion(msg);
        
        Mensaje mensajeCliente=Mensaje.CrearMensajeConParametros(permiso, msg.getParam1(), msg.getParam2());
        os.write(mensajeCliente.toString().getBytes());
        //seguir aqui, leer este mensaje por parte del cliente
        
        /*
        String loquemeLlega = new String(baos.toByteArray());
        System.out.println(loquemeLlega);*/
    }while (true);
    
    
    
  }
  
  public static String Llamar_accion(Mensaje msg){
      //implementar logica del server
      try{
        switch(msg.getAccion()){
            case "Acceso":
              {
                  try {
                      if(IniciarSesion(msg.getParam1(),msg.getParam2())==true){
                          return "Aceptado";
                      }
                      else{
                          return "Denegado";
                      }
                  } catch (IOException ex) {
                      System.out.println("No se encontro el archivo");
                  }
              }
                break;
            case "Registro":
              {
                  try {
                      if(RegistrarUsuario(msg.getParam1(),msg.getParam2())==true){
                          return "Aceptado";
                      }
                      else{
                          return "Denegado";
                      }
                  } catch (IOException ex) {
                      System.out.println("No se encontro el archivo");
                  }
              }
                break;
        }
      }catch(Exception ex){
          
      }
      return "Error inesperado";
  }
  
  public static boolean IniciarSesion(String user,String passw) throws FileNotFoundException, IOException{
      String linea;
      FileReader ficheroLeer=new FileReader("Usuarios y Contraseñas.txt");
      BufferedReader buff=new BufferedReader(ficheroLeer);
      while((linea=buff.readLine())!=null){
          String[] parte=linea.split(";");
          String usuario = parte[0];
          String contraseña = parte[1];
          if(user.equals(usuario) && passw.equals(contraseña)){
              ficheroLeer.close();
              return true;
        }
      }
      ficheroLeer.close();
      return false;
  }
  
  public static boolean RegistrarUsuario(String user,String passw) throws FileNotFoundException, IOException{
      String linea;
      FileReader ficheroLeer=new FileReader("Usuarios y Contraseñas.txt");
      BufferedReader buff=new BufferedReader(ficheroLeer);
      while((linea=buff.readLine())!=null){
          String[] parte=linea.split(";");
          String usuario = parte[0];
          String contraseña = parte[1];
          if(user.equals(usuario)){
              //el usuario ya existe por lo que no se puede volver a registrar
              ficheroLeer.close();
              return false;
        }
      }
      ficheroLeer.close();
      FileWriter ficheroEscribir = new FileWriter("Usuarios y Contraseñas.txt", true);
      ficheroEscribir.write(user+";"+passw+"\n");
      ficheroEscribir.close();
      return true;
  }
  
  
  
  
}