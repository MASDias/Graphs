/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import Entidades.Local;
import Entidades.Personagem;
import graph.AdjacencyMatrixGraph;
import java.util.LinkedList;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MarioDias
 */
public class AliancasTest {
    
    public AliancasTest() {
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
     * Test of conquistarLocais method, of class Aliancas.
     */
    @Test
    public void testConquistarLocais() {
        System.out.println("conquistarLocais");
        Personagem p = null;
        Local localDestino = null;
        AdjacencyMatrixGraph<Local, Double> gameMap = null;
        Aliancas instance = null;
        Map<LinkedList<Local>, Double> expResult = null;
        Map<LinkedList<Local>, Double> result = instance.conquistarLocais(p, localDestino, gameMap);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
