
package piedrapapeltijera;

import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class IULoginTest {
    
    public IULoginTest() {
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
     * Test of main method, of class IULogin.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        IULogin.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initComponents method, of class IULogin.
     */
    @Test
    public void testInitComponents() {
        System.out.println("initComponents");
        IULogin instance = new IULogin();
        instance.initComponents();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initComunication method, of class IULogin.
     */
    @Test
    public void testInitComunication() {
        System.out.println("initComunication");
        IULogin instance = new IULogin();
        instance.initComunication();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class IULogin.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        IULogin instance = new IULogin();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actionPerformed method, of class IULogin.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent e = null;
        IULogin instance = new IULogin();
        instance.actionPerformed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CamposVacios method, of class IULogin.
     */
    @Test
    public void testCamposVacios() {
        System.out.println("CamposVacios");
        String user = "";
        String passw = "";
        IULogin instance = new IULogin();
        boolean expResult = false;
        boolean result = instance.CamposVacios(user, passw);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class IULogin.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String user = "user1";
        String passw = "0000";
        String accion = "retar";
        IULogin instance = new IULogin();
        String expResult = "retar";
        String result = instance.toString(user, passw, accion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of LlamarAccionLogin method, of class IULogin.
     */
    @Test
    public void testLlamarAccionLogin() {
        System.out.println("LlamarAccionLogin");
        Mensaje msg = null;
        IULogin instance = new IULogin();
        instance.LlamarAccionLogin(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
