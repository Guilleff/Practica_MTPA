
package piedrapapeltijera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;


public class Partida extends JFrame implements Runnable,ActionListener{
    private Cliente usuario;
    private Cliente contrincante;
    
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
    
    
    public static void main(String args[]){
        Partida p=new Partida();
    }
    
   //esto solo sirve para comprobar sin tener que venir desde cliente 
    public Partida(){
        Thread hiloPartida = new Thread(this);
        hiloPartida.start();
    }
    //hasta aqui
    
    
    public Partida(Cliente usuario, Cliente contrincante){
        this.usuario=usuario;
        this.contrincante=contrincante;
        
        
        
        Thread hiloPartida = new Thread(this);
        hiloPartida.start();
    }
    
    @Override
    public void run(){
        //aqui meter la logica de la partida
        initComponents();
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public void initComponents(){
        this.setTitle("Partida");
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
        
        
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//quitar pero no ahora, me sirve para comprobar
    }
}
