
package piedrapapeltijera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;


public class Partida extends JFrame implements Runnable,ActionListener{
    
    private String usuario;
    private String contrincante;
    
    private JLabel Tu;
    private JButton Piedra;
    private JButton Papel;
    private JButton Tijera;
    
    private JLabel TuContrincante;
    private JButton PiedraNull;
    private JButton PapelNull;
    private JButton TijeraNull;
    
    private JLabel TandasGanadas;
    private JLabel TandasRestantes;
    private JLabel Tiempo;
    
    private String accion=null;
    private int ganadas=0;
    private int perdida=0;
    private int empate=0;
    private boolean pierdo=false;
    
    //esto solo sirve para comprobar sin tener que venir desde cliente 
    /*public static void main(String args[]){
        Partida p=new Partida();
    }
    
   
    public Partida(){
        Thread hiloPartida = new Thread(this);
        hiloPartida.start();
    }*/
    //hasta aqui
    
    
    public Partida(String usuario, String contrincante){
        this.usuario=usuario;
        this.contrincante=contrincante;
        Thread hiloPartida = new Thread(this);
        hiloPartida.start();
    }
    
    @Override
    public void run(){
        //aqui meter la logica de la partida
        initComponents();
        int tiempo;
        int tandas=3;
        String AccionDeContrincante;
        while(ganadas<2 || tandas>0 || pierdo==false){
            try {
                //seguir aqui
                for(tiempo=5;tiempo>=0;tiempo--){
                    //cambiar esto que da error, no pilla bien la accion si esta parado el hilo
                    Thread.sleep(1000);
                    Tiempo.setText("Tiempo de ronda:"+tiempo+"s");
                }
                if(accion==null){
                    accion="Piedra";
                }
                
                Thread.sleep(1000);
                AccionDeContrincante=Servidor.AccionDeContrincante(usuario,contrincante);
                /*if(AccionDeContrincante==null){
                    AccionDeContrincante="Piedra";
                }*/
                GanadorTanda(accion,AccionDeContrincante);
                BotonesEnabled();
                TandasGanadas.setText("Tandas Ganadas:"+ganadas);
                TandasRestantes.setText("Tandas Restantes:"+(tandas--));
                accion=null;    
            } catch (InterruptedException ex) {
                System.out.println("No se pudo parar el hilo");
            }
        }
        if(ganadas==2){
            Servidor.HeGanadoA(usuario,contrincante);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Piedra){
            setAccion("Piedra");
            Papel.setEnabled(false);
            Tijera.setEnabled(false);
        }
        else if(e.getSource()==Papel){
            setAccion("Papel");
            Piedra.setEnabled(false);
            Tijera.setEnabled(false);
        }
        else if(e.getSource()==Tijera){
            setAccion("Tijera");
            Piedra.setEnabled(false);
            Papel.setEnabled(false);
        }
    }
    
    public void initComponents(){
        this.setTitle("Partida de "+getUsuario());
        this.setSize(400, 475);
        this.setLayout(null);
        
        Tu = new JLabel("Tu");
        Piedra = new JButton("Piedra");
        Papel = new JButton("Papel");
        Tijera = new JButton("Tijera");
        TuContrincante = new JLabel("Tu contrincante");
        PiedraNull = new JButton("Piedra");
        PapelNull = new JButton("Papel");
        TijeraNull = new JButton("Tijera");
        TandasGanadas = new JLabel("Tandas Ganadas:"+0);
        TandasRestantes = new JLabel("Tandas Restantes:"+3);
        Tiempo=new JLabel("Tiempo de ronda: 5s");
        
        Piedra.addActionListener(this);
        Papel.addActionListener(this);
        Tijera.addActionListener(this);
        
        add(Tu);
        Tu.setBounds(100, 35, 20, 20);
        add(Piedra);
        Piedra.setBounds(70, 80, 80, 35);
        add(Papel);
        Papel.setBounds(75, 150, 70, 35);
        add(Tijera);
        Tijera.setBounds(70, 220, 80, 35);
        add(TuContrincante);
        TuContrincante.setBounds(245,35,150,20);
        add(PiedraNull);
        PiedraNull.setBounds(250, 80, 80, 35);
        PiedraNull.setEnabled(false);
        add(PapelNull);
        PapelNull.setBounds(255, 150, 70, 35);
        PapelNull.setEnabled(false);
        add(TijeraNull);
        TijeraNull.setBounds(250, 220, 80, 35);
        TijeraNull.setEnabled(false);
        add(TandasGanadas);
        TandasGanadas.setBounds(50,350,150,20);
        add(TandasRestantes);
        TandasRestantes.setBounds(50,375,150,20);
        add(Tiempo);
        Tiempo.setBounds(200,375,200,20);
        
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//quitar pero no ahora, me sirve para comprobar
    }
    
    public void GanadorTanda(String accion,String accionContrincante){
        if((accion.equals("Piedra") && accionContrincante.equals("Tijera")) || (accion.equals("Papel")&&accionContrincante.equals("Piedra")) ||
           (accion.equals("Tijera") && accionContrincante.equals("Papel"))){
            ganadas++;
        }
        else if(accion.equals(accionContrincante)){
            empate++;
        }
        else{
            perdida++;
        }
    }
    
    public void BotonesEnabled(){
        Piedra.setEnabled(true);
        Papel.setEnabled(true);
        Tijera.setEnabled(true);
    }
    
    public void HasPerdido(){
        pierdo=true;
    }
    
    
    
    public String getAccion() {
        return accion;
    }

    
    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public String getUsuario() {
        return usuario;
    }

  
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

   
    public String getContrincante() {
        return contrincante;
    }

    
    public void setContrincante(String contrincante) {
        this.contrincante = contrincante;
    }
    
}
