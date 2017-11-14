/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import Entidades.Alianca;
import Entidades.Local;
import Entidades.Personagem;
import Ficheiro.LeituraFicheiro;
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
public class MenuPrincipalTest {

    private MenuPrincipal menuPrincipal = new MenuPrincipal();

    public MenuPrincipalTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        String locais = "locais_S.txt";
        String personagens = "pers_S.txt";
        LeituraFicheiro instance = new LeituraFicheiro();
        instance.populateGame(locais, personagens, menuPrincipal);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getGameMap method, of class MenuPrincipal.
     */
    @Test
    public void testGetGameMap() {
        System.out.println("getGameMap");
        MenuPrincipal instance = new MenuPrincipal();
        AdjacencyMatrixGraph<Local, Double> expResult = null;
        AdjacencyMatrixGraph<Local, Double> result = instance.getGameMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAliancas method, of class MenuPrincipal.
     */
    @Test
    public void testGetAliancas() {
        System.out.println("getAliancas");
        MenuPrincipal instance = new MenuPrincipal();
        AdjacencyMatrixGraph<Personagem, Alianca> expResult = null;
        AdjacencyMatrixGraph<Personagem, Alianca> result = instance.getAliancas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of caminhoMaisProximo method, of class MenuPrincipal.
     */
    @Test
    public void testCaminhoMaisProximo() {
        System.out.println("caminhoMaisProximo");
        Local l1 = null;
        Local l2 = null;
        MenuPrincipal instance = new MenuPrincipal();
        LinkedList expResult = null;
        LinkedList result = instance.caminhoMaisProximo(l1, l2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of locaisPersonagemPodeConsquistar method, of class MenuPrincipal.
     */
    @Test
    public void testLocaisPersonagemPodeConsquistar() {
        System.out.println("locaisPersonagemPodeConsquistar");
        Personagem p = new Personagem("Pers1", 53);
        Local l = new Local("Local29", 20, new Personagem("Pers10", 70));

        Map<LinkedList<Local>, Double> expResult = null;
        Map<LinkedList<Local>, Double> result = menuPrincipal.locaisPersonagemPodeConsquistar(p, l);
        
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
