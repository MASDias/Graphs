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
import java.util.HashMap;
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
    
    private MenuPrincipal menuPrincipal = new MenuPrincipal();
    
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
        String locais = "locais_S.txt";
        String personagens = "pers_S.txt";
        LeituraFicheiro instance = new LeituraFicheiro();
        instance.populateGame(locais, personagens, menuPrincipal);
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

    /**
     * Test of aliadosDePersonagem method, of class Aliancas.
     */
    @Test
    public void testAliadosDePersonagem() {
        System.out.println("aliadosDePersonagem");
        Personagem p = null;
        Aliancas instance = null;
        LinkedList<Personagem> expResult = null;
        LinkedList<Personagem> result = instance.aliadosDePersonagem(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of novaAlianca method, of class Aliancas.
     */
    @Test
    public void testNovaAlianca() {
        System.out.println("novaAlianca");
        Personagem C = new Personagem("Pers0", 195);
        Personagem D = new Personagem("Pers2", 112);
        boolean relacao = true;
        double compatibilidade = 0.60;
        Aliancas instance = null;
        boolean expResult = false;
        LinkedList<Personagem> l = new LinkedList<>();
        graphbase.GraphAlgorithms.shortestPath(menuPrincipal.getAliancas(), C, D, l);
        System.out.println(l);
        boolean result = menuPrincipal.novaAlianca(C, D, relacao, compatibilidade);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of aliancaMaisForte method, of class Aliancas.
     */
    @Test
    public void testAliancaMaisForte() {
        System.out.println("aliancaMaisForte");
        Map<LinkedList<Personagem>, Double> expResult = new HashMap<>();
        
        Personagem p1 = new Personagem("Pers0", 195);
        Personagem p2 = new Personagem("Pers3", 429);
        LinkedList<Personagem> listaPersonagens = new LinkedList<>();
        listaPersonagens.add(p1);
        listaPersonagens.add(p2);
        double forca = (p1.getForca() + p2.getForca()) * 0.8;
        
        expResult.put(listaPersonagens, forca);
        
        Map<LinkedList<Personagem>, Double> result = new HashMap<>();
        result = menuPrincipal.aliancaMaisForte();
        assertEquals(expResult, result);
        
    }
    
}
