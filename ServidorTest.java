/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piedrapapeltijera;

import java.net.Socket;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class ServidorTest {
    
    public ServidorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Servidor.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Servidor.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of LlamarAccion method, of class Servidor.
     */
    @Test
    public void testLlamarAccion() {
        System.out.println("LlamarAccion");
        Mensaje msg = null;
        Socket socket = null;
        String expResult = "";
        String result = Servidor.LlamarAccion(msg, socket);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IniciarSesion method, of class Servidor.
     */
    @Test
    public void testIniciarSesion() throws Exception {
        System.out.println("IniciarSesion");
        String user = "user1";
        String passw = "0000";
        boolean expResult = true;
        boolean result = Servidor.IniciarSesion(user, passw);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UsuarioYaConectado method, of class Servidor.
     */
    @Test
    public void testUsuarioYaConectado() {
        System.out.println("UsuarioYaConectado");
        String user = "user1";
        boolean expResult = false;
        boolean result = Servidor.UsuarioYaConectado(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RegistrarUsuario method, of class Servidor.
     */
    @Test
    public void testRegistrarUsuario() throws Exception {
        System.out.println("RegistrarUsuario");
        String user = "user4";
        String passw = "0000";
        boolean expResult = true;
        boolean result = Servidor.RegistrarUsuario(user, passw);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AceptarCliente method, of class Servidor.
     */
    @Test
    public void testAceptarCliente() {
        System.out.println("AceptarCliente");
        Socket sck = null;
        String user = "user1";
        String passw = "0000";
        Servidor.AceptarCliente(sck, user, passw);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of EliminarCliente method, of class Servidor.
     */
    @Test
    public void testEliminarCliente() {
        System.out.println("EliminarCliente");
        String cli = "user2";
        Servidor.EliminarCliente(cli);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListarClientes method, of class Servidor.
     */
    @Test
    public void testListarClientes() {
        System.out.println("ListarClientes");
        ArrayList<String> expResult = new ArrayList<String> ();
        ArrayList<String> result = Servidor.ListarClientes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RetarA method, of class Servidor.
     */
    @Test
    public void testRetarA() {
        System.out.println("RetarA");
        String Retador = "user1";
        String Retado = "user2";
        Servidor.RetarA(Retador, Retado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RetoRechazado method, of class Servidor.
     */
    @Test
    public void testRetoRechazado() {
        System.out.println("RetoRechazado");
        String Retado = "user2";
        String Retador = "user1";
        Servidor.RetoRechazado(Retado, Retador);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RetoAceptado method, of class Servidor.
     */
    @Test
    public void testRetoAceptado() {
        System.out.println("RetoAceptado");
        String Retado = "user2";
        String Retador = "user1";
        Servidor.RetoAceptado(Retado, Retador);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AccionDeContrincante method, of class Servidor.
     */
    @Test
    public void testAccionDeContrincante() {
        System.out.println("AccionDeContrincante");
        String usuario = "piedra";
        String contrincan = "tijera";
        String expResult = "piedra";
        String result = Servidor.AccionDeContrincante(usuario, contrincan);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of HeGanadoA method, of class Servidor.
     */
    @Test
    public void testHeGanadoA() {
        System.out.println("HeGanadoA");
        String usuario = "user1";
        String contrincan = "user2";
        Servidor.HeGanadoA(usuario, contrincan);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of EliminarPartida method, of class Servidor.
     */
    @Test
    public void testEliminarPartida() {
        System.out.println("EliminarPartida");
        String usuario = "user1";
        String contrincan = "user2";
        Servidor.EliminarPartida(usuario, contrincan);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
