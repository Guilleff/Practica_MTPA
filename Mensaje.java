
package piedrapapeltijera;

import java.nio.charset.Charset;


public class Mensaje {
    
    private String accion;
    private String param1;
    private String param2;
    
    public static void Mensaje(){
        
    }
    
    public static Mensaje CrearMensajeConParametros(String accion_,String param1_,String param2_){
        Mensaje msg=new Mensaje();
        msg.setAccion(accion_);
        msg.setParam1(param1_);
        msg.setParam2(param2_);
        return msg;
    }
    
    public static Mensaje CrearMensaje(byte[] buffer){
        Mensaje msg=null;
        String aux = new String(buffer,Charset.forName("UTF-8"));
        String tokens[] = aux.split("@");
        try{
        msg=new Mensaje();
        msg.setAccion(tokens[0]);
        msg.setParam1(tokens[1]);
        msg.setParam2(tokens[2]);
        }catch(Exception ex){
            System.out.println("Error");
        }
        return msg;
    }
   
    public String getAccion() {
        return accion;
    }

   
    public void setAccion(String accion) {
        this.accion = accion;
    }

  
    public String getParam1() {
        return param1;
    }

    
    public void setParam1(String param1) {
        this.param1 = param1;
    }

    
    public String getParam2() {
        return param2;
    }

    
    public void setParam2(String param2) {
        this.param2 = param2;
    }
    
    @Override
    public String toString(){
        return this.accion + "@" + this.param1 + "@" + this.param2;
  }
}
