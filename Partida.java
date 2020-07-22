
package piedrapapeltijera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Clase en la que se lleva a cabo las partidas entre dos usuarios
 * @author david
 * @version 1.0
 */

public class Partida extends JFrame implements Runnable,ActionListener{
    
    /**
     * Definimos los objetos que vamos a usar en nuestra clase
     */
    
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
    
    private String accion="Piedra";
    private float TandasTotales=3;
    private float ganadas=0;
    private int perdida=0;
    private float empate=0;
    private boolean pierdo=false;
    private boolean empatamos=false;
    private boolean gano=false;
    
    /**
     * Metodo que empieza una partida entre dos usuarios
     * @param usuario un usuario
     * @param contrincante otro usuario
     */
    
    public Partida(String usuario, String contrincante){
        this.usuario=usuario;
        this.contrincante=contrincante;
        Thread hiloPartida = new Thread(this);
        hiloPartida.start();
    }
    
    @Override
    public void run(){
        initComponents();
        int tiempo;
        int tandas=(int)TandasTotales;
        String AccionDeContrincante;
        while(((TandasTotales-empate)/2)>ganadas && pierdo==false && tandas!=0 && gano==false){
            try {
                //seguir aqui
                for(tiempo=5;tiempo>=0;tiempo--){
                    Thread.sleep(1000);
                    Tiempo.setText("Tiempo de ronda:"+tiempo+"s");
                }
                Thread.sleep(1000);
                AccionDeContrincante=Servidor.AccionDeContrincante(usuario,contrincante);
                BotonContrincante(AccionDeContrincante);
                Thread.sleep(2000);
                GanadorTanda(accion,AccionDeContrincante);
                BotonesEnabled();
                TandasGanadas.setText("Tandas Ganadas:"+(int)ganadas);
                tandas--;
                TandasRestantes.setText("Tandas Restantes:"+tandas);
                accion="Piedra";    
                BotonesContrincanteNull();
            } catch (InterruptedException ex) {
                System.out.println("No se pudo parar el hilo");
            }
        }
        if(((TandasTotales-empate)/2)<ganadas || gano==true){
            Servidor.HeGanadoA(usuario,contrincante);
            JOptionPane.showMessageDialog(this, "Enhorabuena, has ganado", "Victoria", JOptionPane.INFORMATION_MESSAGE, null);
        }
        else if(empate==TandasTotales){
            JOptionPane.showMessageDialog(this, "Ninguno ha ganado, es un empate", "Empate", JOptionPane.INFORMATION_MESSAGE, null);
            empatamos=true;
        }
        else{
            JOptionPane.showMessageDialog(this, "Has perdido,mejor suerte la proxima vez", "Derrota", JOptionPane.INFORMATION_MESSAGE, null);
        }
        
    }
    
    /**
     * Metodo que indica que accion se lleva a cabo en la partida
     * @param e 
     */
    
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
    
    /**
     * Metodo que inicializa los componentes y los botones de la partida, tanto
     * del usuario como del oponente
     */
    
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
        
        addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
                Servidor.EliminarPartida(usuario,contrincante);
                Servidor.PartidasFinalizadas(usuario, contrincante);
                pierdo=true;
                dispose();
        }
        });

        
        
    }
    
    /**
     * Metodo que suma rondas ganadas o perdidas
     * @param accion accion que realiza el usuario
     * @param accionContrincante accion que realiza el oponente
     */
    
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
    
    /**
     * Metodo que muestra los botones de pieddra, papel y tijera
     */
    
    public void BotonesEnabled(){
        Piedra.setEnabled(true);
        Papel.setEnabled(true);
        Tijera.setEnabled(true);
    }
    
    /**
     * Metodo que indica que has perdido
     */
    
    public void HasPerdido(){
        pierdo=true;
    }
    
    /**
     * Metodo que indica que has ganado
     */
    
    public void GanadaPorRendicion(){
        gano=true;
    }
    
    /**
     * Metodo que muestra los botones del oponente
     * @param AccionContrincante 
     */
    
    public void BotonContrincante(String AccionContrincante){
        switch(AccionContrincante){
            case "Piedra":
                PiedraNull.setEnabled(true);
                break;
            case "Papel":
                PapelNull.setEnabled(true);
                break;
            case "Tijera":
                TijeraNull.setEnabled(true);
                break;
        }
    }
    
    /**
     * Metodo que oculta los botones del oponente
     */
    
    public void BotonesContrincanteNull(){
        PiedraNull.setEnabled(false);
        PapelNull.setEnabled(false);
        TijeraNull.setEnabled(false);
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
