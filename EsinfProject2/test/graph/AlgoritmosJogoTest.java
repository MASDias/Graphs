/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import Entidades.Local;
import Ficheiro.LeituraFicheiro;
import MainMenu.MenuPrincipal;
import java.util.LinkedList;
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
public class AlgoritmosJogoTest {

    MenuPrincipal menu = new MenuPrincipal();

    public AlgoritmosJogoTest() {
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
        instance.populateGame(locais, personagens, menu);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of conquistaMaisFacil method, of class AlgoritmosJogo.
     */
    @Test
    public void testShortestPath() {
        System.out.println("shortestPath");
        AdjacencyMatrixGraph<Local, Double> graph = menu.getGameMap();
        Local source = new Local("Local2", 21);
        Local dest = new Local("Local20", 25);
        LinkedList<Local> path = new LinkedList<>();
        double expResult = 0.0;
        double result = AlgoritmosJogo.conquistaMaisFacil(graph, source, dest, path);
        System.out.println(path);
        System.out.println(result);
//        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
