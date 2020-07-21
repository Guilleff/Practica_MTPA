
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

/**
 * Clase servidor en la que se conectan los usuarios y se guardan las partidas
 * @author david
 * @version 1.0
 */

public class Servidor {
    
    /**
     * Definimos dos arraylist una de usuario y otra de la partida
     */
    
    private static ArrayList<Cliente> usuariosConectados = new ArrayList<>();
    private static ArrayList<Partida> partidasEnCurso = new ArrayList<>();
    
    public static void main (String args[]) throws Exception
  {
    
    
    ServerSocket srv = new ServerSocket(9998);
    System.out.println("Servidor Arrancado");
    do{
        Socket sck =  srv.accept();
        InputStream is = sck.getInputStream();
        OutputStream os= sck.getOutputStream();
        byte[] buffer = new byte[16];
        int nb;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        do{

            nb = is.read(buffer);
            baos.write(buffer, 0, nb);
        }while (nb > 0 && is.available() > 0);
        Mensaje msg=Mensaje.CrearMensaje(baos.toByteArray());
        String permiso=LlamarAccion(msg,sck);
        Mensaje mensajeCliente=Mensaje.CrearMensajeConParametros(permiso, msg.getParam1(), msg.getParam2());
        os.write(mensajeCliente.toString().getBytes("UTF-8"));
    }while (true);
  }
  
    /**
     * Metodo que permite la conexion de un cliente o el regisro
     * @param msg
     * @param socket
     * @return devuelve un aceptado o denegado
     */
    
  public static String LlamarAccion(Mensaje msg,Socket socket){
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
          System.out.println("Error");
      }
      return "Error inesperado";
  }
  
  /**
   * Metodo para iniciar sesion con un usuario
   * @param user usuario
   * @param passw contraseña del usuario
   * @return
   * @throws FileNotFoundException
   * @throws IOException 
   */
  
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
  
  /**
   * Metodo que nos indica si el usuario esta concetado
   * @param user usuario
   * @return devuelve true si el usuario esta conectado
   */
  
  public static boolean UsuarioYaConectado(String user){
      for(Cliente aux: usuariosConectados){
          if(aux.getUsuario().equals(user)){
              return true;
          }
      }
      return false;
  }
  
  /**
   * Metodo para registrar a un usuario
   * @param user usuario
   * @param passw contraseña del usuario
   * @return
   * @throws FileNotFoundException
   * @throws IOException 
   */
  
  public static boolean RegistrarUsuario(String user,String passw) throws FileNotFoundException, IOException{
      String linea;
      FileReader ficheroLeer=new FileReader("Usuarios y Contraseñas.txt");
      BufferedReader buff=new BufferedReader(ficheroLeer);
      while((linea=buff.readLine())!=null){
          String[] parte=linea.split(";");
          String usuario = parte[0];
          String contraseña = parte[1];
          if(user.equals(usuario)){
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
  
  /**
   * Metodo que acepta a un usuario y lo añade a la lista de usuarios conectados
   * @param sck
   * @param user
   * @param passw 
   */
  
  public static void AceptarCliente(Socket sck,String user,String passw){
      Cliente cliente = new Cliente(sck,user,passw);
      usuariosConectados.add(cliente);
      usuariosConectados.forEach((actualizar) -> {
          actualizar.ActualizarJList();
        });
  }
  
  /**
   * Metodo que elimina a un cliente de la lista de concetados
   * @param cli hace referencia a un usuario o cliente
   */
  
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
  
  /**
   * Metodo que nos muestra la lista de clientes conectados
   * @return devuelve los usuarios conectados
   */
  
  public static ArrayList<String> ListarClientes(){
      ArrayList<String> users=new ArrayList<String>();
      for (Cliente unusuario : usuariosConectados){
          users.add(unusuario.getUsuario());
      }
      return users;
  }
  
  /**
   * Metodo que sirve para retar a jugadores
   * @param Retador quien reta
   * @param Retado quien es retado
   */
  
  public static void RetarA(String Retador,String Retado){
      for (Cliente unusuario : usuariosConectados){
          if(unusuario.getUsuario().equals(Retado)){
              unusuario.RetadoPor(Retador);
          }
      }
  }
  
  /**
   * Metodo que indica si el reto se rechaza
   * @param Retado
   * @param Retador 
   */
  
  public static void RetoRechazado(String Retado,String Retador){
      for (Cliente unusuario : usuariosConectados){
          if(unusuario.getUsuario().equals(Retador)){
              unusuario.RechazaronReto(Retado);
          }
      }
  }
  
  /**
   * MEtodo que indica si el reto es aceptado
   * @param Retado
   * @param Retador 
   */
  
  public static void RetoAceptado(String Retado,String Retador){
      Partida P=new Partida(Retado,Retador);
      Partida P2=new Partida(Retador,Retado);
      partidasEnCurso.add(P);
      partidasEnCurso.add(P2);
  }
  
  /**
   * Metodo que indica las acciones que realiza un oponente
   * @param usuario un usuario
   * @param contrincan oponente
   * @return 
   */
  
  public static String AccionDeContrincante(String usuario,String contrincan){
      for(Partida aux:partidasEnCurso){
          if(aux.getUsuario().equals(contrincan) && aux.getContrincante().equals(usuario)){
              return aux.getAccion();
          }
      }
      return "Piedra";
  }
  
  /**
   * Metodo que indica quien gana una partida
   * @param usuario
   * @param contrincan 
   */
  
  public static void HeGanadoA(String usuario,String contrincan){
    for(Partida aux:partidasEnCurso){
          if(aux.getUsuario().equals(contrincan) && aux.getContrincante().equals(usuario)){
              aux.HasPerdido();
          }
      }
  }
  
  /**
   * Metodo para eliminar una partida en curso
   * @param usuario
   * @param contrincan 
   */
  
  public static void EliminarPartida(String usuario,String contrincan){
      ArrayList<Partida> listaAux=new ArrayList<Partida>();
      for(Partida p: partidasEnCurso){
          if(p.getUsuario().equals(usuario) && p.getContrincante().equals(contrincan)){
              listaAux.add(p);
          }
      }
      partidasEnCurso.removeAll(listaAux);
      for(Partida p: partidasEnCurso){
          if(p.getUsuario().equals(contrincan) && p.getContrincante().equals(usuario)){
              p.GanadaPorRendicion();
          }
      }
  }
  
}
