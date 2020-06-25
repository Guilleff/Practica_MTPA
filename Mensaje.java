
package piedrapapeltijera;


public class Mensaje {

    
    private String accion;
    private String param1;
    private String param2;
    
    
    public static void Mensaje(){
        
    }
    
    public static Mensaje CrearMensaje(byte[] buffer){
        Mensaje msg=null;
        String aux = new String(buffer);
        String tokens[] = aux.split("@");
        msg.setAccion(tokens[0]);
        msg.setParam1(tokens[1]);
        msg.setParam2(tokens[2]);
        
        
        
        
        
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
}
