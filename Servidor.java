
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
import java.util.ArrayList;


public class Servidor {
    
    private static ArrayList<Cliente> usuariosConectados = new ArrayList<>();
    
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
        
        Mensaje msg=Mensaje.CrearMensaje(baos.toByteArray());
        
        
        
        String permiso=LlamarAccion(msg,sck);
        
        Mensaje mensajeCliente=Mensaje.CrearMensajeConParametros(permiso, msg.getParam1(), msg.getParam2());
        os.write(mensajeCliente.toString().getBytes("UTF-8"));
        
        
        
        /*
        String loquemeLlega = new String(baos.toByteArray());
        System.out.println(loquemeLlega);*/
    }while (true);
    
    
    
  }
  
  public static String LlamarAccion(Mensaje msg,Socket socket){
      //implementar logica del server
      try{
        switch(msg.getAccion()){
            case "Acceso":
              {
                  try {
                      if(IniciarSesion(msg.getParam1(),msg.getParam2())==true && UsuarioYaConectado(msg.getParam1())==false){
                          AceptarCliente(socket,msg.getParam1(),msg.getParam2());
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
                          AceptarCliente(socket,msg.getParam1(),msg.getParam2());
                          return "Aceptado";
                      }
                      else{
                          return "DenegadoE";
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
          if(user.equals(usuario) && passw.equals(contraseña)){//poner que no se puede entrar con un usuario si esta conectado
              ficheroLeer.close();
              return true;
        }
      }
      ficheroLeer.close();
      return false;
  }
  
  public static boolean UsuarioYaConectado(String user){
      for(Cliente aux: usuariosConectados){
          if(aux.getUsuario().equals(user)){
              return true;
          }
      }
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
  
  public static void AceptarCliente(Socket sck,String user,String passw){
      Cliente cliente = new Cliente(sck,user,passw);
      usuariosConectados.add(cliente);
      usuariosConectados.forEach((actualizar) -> {
          actualizar.ActualizarJList();
        });
  }
  public static void EliminarCliente(String cli){
      ArrayList<Cliente> listaAux=new ArrayList<Cliente>();
      for(Cliente user: usuariosConectados){
          if(user.getUsuario().equals(cli)){
              listaAux.add(user);
          }
      }
      usuariosConectados.removeAll(listaAux);
      usuariosConectados.forEach((user) -> {
          user.ActualizarJList();
        });
  }
  
  public static ArrayList<String> ListarClientes(){
      ArrayList<String> users=new ArrayList<String>();
      for (Cliente unusuario : usuariosConectados){
          users.add(unusuario.getUsuario());
      }
      return users;
  }
  
  public static void RetarA(String Retador,String Retado){
      for (Cliente unusuario : usuariosConectados){
          if(unusuario.getUsuario().equals(Retado)){
              unusuario.RetadoPor(Retador);
          }
      }
  }
  
  public static void RetoRechazado(String Retado,String Retador){
      for (Cliente unusuario : usuariosConectados){
          if(unusuario.getUsuario().equals(Retador)){
              unusuario.RechazaronReto(Retado);
          }
      }
  }
}
