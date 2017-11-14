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

    /**
     * Test of aliadosDePersonagem method, of class MenuPrincipal.
     */
    @Test
    public void testAliadosDePersonagem() {
        System.out.println("aliadosDePersonagem");
        Personagem p = new Personagem("Pers0", 47);
        LinkedList<Personagem> expResult = null;
        LinkedList<Personagem> result = menuPrincipal.aliadosDePersonagem(p);
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of novaAlianca method, of class MenuPrincipal.
     */
    @Test
    public void testNovaAlianca() {
        
        boolean result = menuPrincipal.novaAlianca(new Personagem("Teste", 12), new Personagem("Pers0", 47), true, 0.12);
        assertFalse("Nao existe Personanm, tem que ser falso", result); 
        result = menuPrincipal.novaAlianca(new Personagem("Pers0", 47), new Personagem("Pers0", 47), true, 0.12);
        assertFalse("Mesma personagem, tem que ser resultado falso", result); 
        
        result = menuPrincipal.novaAlianca(new Personagem("Pers8", 46), new Personagem("Pers0", 47), true, 1.1);
        assertFalse("Compatibilidade acima de 1, resulta tem que ser false", result);
        result = menuPrincipal.novaAlianca(new Personagem("Pers8", 46), new Personagem("Pers0", 47), true, -1);
        assertFalse("Compatibilidade abaixo de 0, resulta tem que ser false", result);
        
        System.out.println("Nova Alianca entre [A  B] e [C  D]");
        Personagem A = new Personagem("Pers0", 47);
        Personagem B = new Personagem("Pers7", 59);
        boolean Relacao = true;
        double compatibilidade = 0.45;
        boolean expResult = false;
        result = menuPrincipal.novaAlianca(A, B, Relacao, compatibilidade);
        assertEquals("Alianca ja existe entre A e B tem que ser false", expResult, result);
        Personagem C = new Personagem("Pers0", 47);
        Personagem D = new Personagem("Pers8", 46);
        Relacao = true;
        compatibilidade = 0.60;
        expResult = true;
        result = menuPrincipal.novaAlianca(C, D, Relacao, compatibilidade);
        assertEquals("Tem que ser criada nova  alianca entre C e D, ainda nao existe alianca feita", expResult, result);
       
    }

}
