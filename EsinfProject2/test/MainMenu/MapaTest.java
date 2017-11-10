/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import Entidades.Local;
import Ficheiro.LeituraFicheiro;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class MapaTest {
    
    private MenuPrincipal menuPrincipal = new MenuPrincipal();
    
    public MapaTest() {
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
     * Test of caminhoMaisFacil method, of class Mapa.
     */
    @Test
    public void testCaminhoMaisFacil() {
        System.out.println("caminhoMaisFacil");
        Local l1 = new Local("Local25", 32);
        Local l2 = new Local("Local29", 20);
        Local l3 = new Local("Local30", 31);
        LinkedList<Local> expResult = new LinkedList<>();
        expResult.add(l1);
        expResult.add(l2);
        expResult.add(l3);
        
        LinkedList<Local> result = menuPrincipal.caminhoMaisProximo(l1, l3);
        assertEquals(expResult, result);
    }
    
}
