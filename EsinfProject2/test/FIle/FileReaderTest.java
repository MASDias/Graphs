/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FIle;

import Entidades.Personagem;
import File.LeituraFicheiro;
import MainMenu.MenuPrincipal;
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
public class FileReaderTest {
    
    private MenuPrincipal menuPrincipal = new MenuPrincipal();
    
    public FileReaderTest() {
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
     * Test of populateGame method, of class LeituraFicheiro.
     */
    @Test
    public void testPopulateGame() {
        System.out.println("Leitura  para popular e adicionar locais para o jogo");
        String nomeLocais = "locais_S.txt";
        String nomePersonagens = "pers_S.txt";
        LeituraFicheiro instance = new LeituraFicheiro();
        
        instance.populateGame(nomeLocais, nomePersonagens, menuPrincipal);
        for (Personagem vertice : menuPrincipal.getAliancas().vertices()) {
            System.out.println(vertice);
        }
    }
    
}
