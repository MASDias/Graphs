/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import Entidades.AliancaMaisForte;
import Entidades.Conquista;
import Entidades.Local;
import Entidades.Personagem;
import Ficheiro.LeituraFicheiro;
import graphbase.Graph;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
     * Test of caminhoMaisProximo method, of class MenuPrincipal.
     */
    @Test
    public void testCaminhoMaisProximo() {
        System.out.println("Teste caminho mais proximo");
        Local verticeInvalido = new Local("Invalido", 500);
        Local verticeValido = new Local("Local0", 30);
        LinkedList result = menuPrincipal.caminhoMaisProximo(verticeInvalido, verticeValido);
        assertNull(result);

        Local l1 = new Local("Local0", 30);
        Local l2 = new Local("Local29", 31);
        LinkedList<Local> expResult = new LinkedList<>();
        expResult.add(new Local("Local0", 30));
        expResult.add(new Local("Local28", 20));
        expResult.add(new Local("Local29", 31));
        result = menuPrincipal.caminhoMaisProximo(l1, l2);
        assertEquals("O caminho sera 0 - 28 - 29", expResult, result);

        Local l3 = new Local("Local25", 32);
        Local l4 = new Local("Local29", 20);
        Local l5 = new Local("Local30", 31);
        expResult = new LinkedList<>();
        expResult.add(l3);
        expResult.add(l4);
        expResult.add(l5);

        result = menuPrincipal.caminhoMaisProximo(l3, l5);
        assertEquals(expResult, result);
    }

    /**
     * Test of aliadosDePersonagem method, of class MenuPrincipal.
     */
    @Test
    public void testAliadosDePersonagem() {
        System.out.println("aliadosDePersonagem");
        Personagem p = new Personagem("Pers0", 195);
        LinkedList<Personagem> expResult = new LinkedList<>();
        Personagem Aliado1 = new Personagem("Pers3", 429);
        Personagem Aliado2 = new Personagem("Pers4", 262);
        expResult.add(Aliado1);
        expResult.add(Aliado2);
        LinkedList<Personagem> result = menuPrincipal.aliadosDePersonagem(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of novaAlianca method, of class MenuPrincipal.
     */
    @Test
    public void testNovaAlianca() {
        boolean result = menuPrincipal.novaAlianca(new Personagem("Teste", 12), new Personagem("Pers0", 195), true, 0.12);
        assertFalse("Nao existe Personanm, tem que ser falso", result);
        result = menuPrincipal.novaAlianca(new Personagem("Pers0", 195), new Personagem("Pers0", 195), true, 0.12);
        assertFalse("Mesma personagem, tem que ser resultado falso", result);

        result = menuPrincipal.novaAlianca(new Personagem("Pers8", 481), new Personagem("Pers0", 195), true, 1.1);
        assertFalse("Compatibilidade acima de 1, resulta tem que ser false", result);
        result = menuPrincipal.novaAlianca(new Personagem("Pers8", 481), new Personagem("Pers0", 195), true, -1);
        assertFalse("Compatibilidade abaixo de 0, resulta tem que ser false", result);

        System.out.println("Nova Alianca entre [A  B] e [C  D]");
        Personagem A = new Personagem("Pers0", 195);
        Personagem B = new Personagem("Pers3", 429);
        boolean Relacao = true;
        double compatibilidade = 0.45;
        boolean expResult = false;
        result = menuPrincipal.novaAlianca(A, B, Relacao, compatibilidade);
        assertEquals("Alianca ja existe entre A e B tem que ser false", expResult, result);
        Personagem C = new Personagem("Pers0", 195);
        Personagem D = new Personagem("Pers8", 481);
        Relacao = true;
        compatibilidade = 0.60;
        expResult = true;
        result = menuPrincipal.novaAlianca(C, D, Relacao, compatibilidade);
        assertEquals("Tem que ser criada nova alianca direta entre C e D, ainda nao existe alianca feita", expResult, result);

        Personagem E = new Personagem("Pers0", 195);
        Personagem F = new Personagem("Pers2", 112);
        Relacao = true;
        compatibilidade = 0.3;
        expResult = true;
        result = menuPrincipal.novaAlianca(E, F, Relacao, compatibilidade);
        assertEquals("Tem que ser criada nova alianca direta entre E e F, ainda nao existe alianca feita", expResult, result);
    }

    /**
     * Test of locaisPersonagemPodeConsquistar method, of class MenuPrincipal.
     */
    @Test
    public void testLocaisPersonagemPodeConsquistar() {
        System.out.println("Locais Personagem pode conquistar");
        Personagem p = new Personagem("Pers8", 481);
        Local localDestino = new Local("Local30", 30);
        Local localAtual = new Local("Local18", 30, p);
        Conquista result = menuPrincipal.locaisPersonagemPodeConsquistar(p, localAtual, localDestino);
        LinkedList<Local> expList = new LinkedList<>();
        expList.add(new Local("Local18", 30, new Personagem("Pers8", 481)));
        expList.add(new Local("Local28", 20));
        expList.add(new Local("Local29", 31));
        expList.add(new Local("Local30", 30));
        double expForca = 155.0;
        boolean expSuccess = true;
        Conquista expResult = new Conquista(expSuccess, expList, expForca);
        assertEquals(expResult, result);

        MenuPrincipal instance = new MenuPrincipal();

        Personagem A = new Personagem("A", 200);
        Personagem B = new Personagem("B", 300);
        Personagem C = new Personagem("C", 150);
        Personagem D = new Personagem("D", 200);

        instance.adicionarPersonagem(A);
        instance.adicionarPersonagem(B);
        instance.adicionarPersonagem(C);
        instance.adicionarPersonagem(D);

        Local l1 = new Local("Local1", 30, A);
        Local l2 = new Local("Local2", 50, A);
        Local l3 = new Local("Local3", 50);
        Local l4 = new Local("Local4", 37);
        Local l5 = new Local("Local5", 20);
        Local l6 = new Local("Local6", 10);

        instance.adicionarLocalJogo(l1);
        instance.adicionarLocalJogo(l2);
        instance.adicionarLocalJogo(l3);
        instance.adicionarLocalJogo(l4);
        instance.adicionarLocalJogo(l5);
        instance.adicionarLocalJogo(l6);

        instance.insertEstradaLocal(l2, l1, 20);
        instance.insertEstradaLocal(l2, l3, 10);
        instance.insertEstradaLocal(l3, l5, 30);
        instance.insertEstradaLocal(l5, l6, 100);
        instance.insertEstradaLocal(l1, l4, 30);
        instance.insertEstradaLocal(l4, l6, 20);
        instance.insertEstradaLocal(l4, l3, 40);

        LinkedList<Local> expList2 = new LinkedList<>();
        expList2.add(l2);
        expList2.add(l1);
        expList2.add(l4);
        expList2.add(l6);

        Conquista expResultConquista = new Conquista(Boolean.TRUE, expList2, 117);
        Conquista resultConquista = instance.locaisPersonagemPodeConsquistar(A, l2, l6);

        assertEquals(expResultConquista, resultConquista);

        l4.setDono(D);

        expList2.clear();
        expList2.add(l2);
        expList2.add(l3);
        expList2.add(l5);
        expList2.add(l6);

        expResultConquista = new Conquista(Boolean.FALSE, expList2, 220);
        resultConquista = instance.locaisPersonagemPodeConsquistar(A, l2, l6);
        assertEquals(expResultConquista, resultConquista);
    }

    /**
     * Test of aliancaMaisForte method, of class MenuPrincipal.
     */
    @Test
    public void testAliancaMaisForte() {
        System.out.println("Alianca mais forte do jogo");
        LinkedList<Personagem> expAlianca = new LinkedList<>();
        expAlianca.add(new Personagem("Pers0", 195));
        expAlianca.add(new Personagem("Pers3", 429));
        double expForcaAlianca = 408.5;
        AliancaMaisForte expResult = new AliancaMaisForte(expAlianca, expForcaAlianca);
        AliancaMaisForte result = menuPrincipal.aliancaMaisForte();
        assertEquals(expResult, result);

    }

    /**
     * Test of verificarConquistaComAliado method, of class MenuPrincipal.
     */
    @Test
    public void testVerificarConquistaComAliado() {
        System.out.println("verificarConquistaComAliado");
        Personagem personagem = new Personagem("Pers0", 195);
        Personagem aliado = new Personagem("Pers3", 429);
        Local localP = new Local("Local2", 21);
        Local alvo = new Local("Local20", 25);
        boolean expSuccess = false;
        LinkedList<Local> expList = new LinkedList<>();
        expList.add(new Local("Local2", 21, new Personagem("Pers0", 195)));
        expList.add(new Local("Local3", 23));
        expList.add(new Local("Local9", 29));
        expList.add(new Local("Local20", 25, new Personagem("Pers9", 463)));
        double expForca = 622.0;
        Conquista expResult = new Conquista(expSuccess, expList, expForca, aliado);
        Conquista result = menuPrincipal.verificarConquistaComAliado(personagem, aliado, localP, alvo);
        assertEquals(expResult, result);

        MenuPrincipal instance = new MenuPrincipal();
        Personagem A = new Personagem("A", 400);
        Personagem B = new Personagem("B", 300);
        Personagem C = new Personagem("C", 150);
        Personagem D = new Personagem("D", 200);

        instance.adicionarPersonagem(A);
        instance.adicionarPersonagem(B);
        instance.adicionarPersonagem(C);
        instance.adicionarPersonagem(D);

        instance.insertAlianca(A, B, Boolean.TRUE, 0.8);
        instance.insertAlianca(A, D, Boolean.TRUE, 0.5);

        Local l1 = new Local("Local1", 50, A);
        Local l2 = new Local("Local2", 50, A);
        Local l3 = new Local("Local3", 70, B);
        Local l4 = new Local("Local4", 37, D);
        Local l5 = new Local("Local5", 50, B);
        Local l6 = new Local("Local6", 10, C);
        Local l7 = new Local("Local7", 40);

        instance.adicionarLocalJogo(l1);
        instance.adicionarLocalJogo(l2);
        instance.adicionarLocalJogo(l3);
        instance.adicionarLocalJogo(l4);
        instance.adicionarLocalJogo(l5);
        instance.adicionarLocalJogo(l6);
        instance.adicionarLocalJogo(l7);

        instance.insertEstradaLocal(l1, l2, 20);
        instance.insertEstradaLocal(l1, l7, 27);
        instance.insertEstradaLocal(l7, l4, 30);
        instance.insertEstradaLocal(l4, l6, 17);
        instance.insertEstradaLocal(l4, l3, 53);
        instance.insertEstradaLocal(l3, l5, 28);
        instance.insertEstradaLocal(l2, l3, 18);

        LinkedList<Local> expList2 = new LinkedList<>();
        expList2.add(l2);
        expList2.add(l1);
        expList2.add(l7);
        expList2.add(l4);
        expList2.add(l6);
        Conquista expResultConquista = new Conquista(Boolean.TRUE, expList2, 531, B);
        Conquista resultConquista = instance.verificarConquistaComAliado(A, B, l2, l6);
        assertEquals(expResultConquista, resultConquista);

        l4.setDono(B);
        expResultConquista = new Conquista(Boolean.FALSE, new LinkedList<Local>(), 0, B);
        resultConquista = instance.verificarConquistaComAliado(A, B, l2, l6);
        assertEquals(expResultConquista, resultConquista);

        resultConquista = instance.verificarConquistaComAliado(new Personagem("Invalida", 123), B, l2, l6);
        assertNull(resultConquista);
        resultConquista = instance.verificarConquistaComAliado(A, new Personagem("Invalida", 123), l2, l6);
        assertNull(resultConquista);
        resultConquista = instance.verificarConquistaComAliado(A, B, new Local("LocalInvalido", 12), l6);
        assertNull(resultConquista);
        resultConquista = instance.verificarConquistaComAliado(A, B, l2, new Local("LocalInvalido", 12));
        assertNull(resultConquista);

        l4.setDono(D);
        Local l8 = new Local("Local8", 5);
        instance.adicionarLocalJogo(l8);

        instance.insertEstradaLocal(l7, l8, 10);
        instance.insertEstradaLocal(l8, l6, 15);

        expList2.clear();
        expList2.add(l2);
        expList2.add(l1);
        expList2.add(l7);
        expList2.add(l8);
        expList2.add(l6);
        expResultConquista = new Conquista(Boolean.TRUE, expList2, 277, B);
        resultConquista = instance.verificarConquistaComAliado(A, B, l2, l6);
        assertEquals(expResultConquista, resultConquista);
    }

    /**
     * Test of novasAliancasPossiveis method, of class MenuPrincipal.
     */
    @Test
    public void testNovasAliancasPossiveis() {
        System.out.println("Possiveis novas alianças (inclusive antigas)");

        Graph<Personagem, Boolean> expResult = new Graph<>(false);
        Personagem p0 = new Personagem("Pers0", 195);
        Personagem p1 = new Personagem("Pers1", 111);
        Personagem p2 = new Personagem("Pers2", 112);
        Personagem p3 = new Personagem("Pers3", 429);
        Personagem p4 = new Personagem("Pers4", 262);
        Personagem p5 = new Personagem("Pers5", 126);
        Personagem p6 = new Personagem("Pers6", 121);
        Personagem p7 = new Personagem("Pers7", 354);
        Personagem p8 = new Personagem("Pers8", 481);
        Personagem p9 = new Personagem("Pers9", 463);
        expResult.insertVertex(p0);
        expResult.insertVertex(p1);
        expResult.insertVertex(p2);
        expResult.insertVertex(p3);
        expResult.insertVertex(p4);
        expResult.insertVertex(p5);
        expResult.insertVertex(p6);
        expResult.insertVertex(p7);
        expResult.insertVertex(p8);
        expResult.insertVertex(p9);

        expResult.insertEdge(p0, p3, Boolean.TRUE, 0.8);
        expResult.insertEdge(p0, p4, Boolean.TRUE, 0.5);
        expResult.insertEdge(p1, p2, Boolean.TRUE, 0.5);
        expResult.insertEdge(p1, p3, Boolean.TRUE, 0.5);
        expResult.insertEdge(p1, p4, Boolean.TRUE, 0.2);
        expResult.insertEdge(p3, p4, Boolean.TRUE, 0.5);
        expResult.insertEdge(p5, p7, Boolean.FALSE, 0.5);
        expResult.insertEdge(p6, p8, Boolean.TRUE, 0.8);
        expResult.insertEdge(p7, p8, Boolean.TRUE, 0.5);
        expResult.insertEdge(p7, p9, Boolean.TRUE, 0.5);
        expResult.insertEdge(p0, p1, Boolean.TRUE, 0.65);
        expResult.insertEdge(p0, p2, Boolean.TRUE, 0.575);
        expResult.insertEdge(p3, p2, Boolean.TRUE, 0.5);
        expResult.insertEdge(p2, p4, Boolean.TRUE, 0.35);
        expResult.insertEdge(p5, p8, Boolean.TRUE, 0.5);
        expResult.insertEdge(p5, p9, Boolean.TRUE, 0.5);
        expResult.insertEdge(p5, p6, Boolean.TRUE, 0.65);
        expResult.insertEdge(p6, p7, Boolean.TRUE, 0.575);
        expResult.insertEdge(p6, p9, Boolean.TRUE, 0.575);
        expResult.insertEdge(p8, p9, Boolean.TRUE, 0.5);

        Graph<Personagem, Boolean> result = menuPrincipal.novasAliancasPossiveis();

        assertEquals(expResult, result);

    }

    /**
     * Test of insertAlianca method, of class MenuPrincipal.
     */
    @Test
    public void testInsertAlianca() {
        System.out.println("Inserir alianca");
        Personagem p1 = new Personagem("Teste 1", 455);
        Personagem p2 = new Personagem("Teste 2", 44);
        Personagem p3 = new Personagem("Teste 3", 90);
        Personagem p4 = new Personagem("Teste 4", 74);
        boolean relacao = false;
        double compatibilidade = 0.45;
        MenuPrincipal instance = new MenuPrincipal();
        instance.adicionarPersonagem(p1);
        instance.adicionarPersonagem(p2);
        instance.adicionarPersonagem(p3);
        instance.adicionarPersonagem(p4);
        boolean booleanResult = instance.insertAlianca(p1, p2, relacao, compatibilidade);
        int expResult = 2;
        assertTrue("Espera true a alianca foi inserida", booleanResult);
        int result = instance.getGraphAliancas().numEdges();
        assertEquals("Espera 2 edges novo", expResult, result);
        booleanResult = instance.insertAlianca(p1, p3, relacao, compatibilidade);
        expResult = 4;
        result = instance.getGraphAliancas().numEdges();
        assertTrue("Espera 2 edges novos", booleanResult);
        assertEquals("Espera 4 edges novos", expResult, result);

        booleanResult = instance.insertAlianca(p1, p2, relacao, compatibilidade);
        expResult = 4;
        result = instance.getGraphAliancas().numEdges();
        assertFalse("Espera false ja existe aliancaentre p1 e p2", booleanResult);
        assertEquals("Espera 4 edges pois ja existe relacao entre p1 e p2", expResult, result);

        compatibilidade = 1.1;
        booleanResult = instance.insertAlianca(p1, p2, relacao, compatibilidade);
        expResult = 4;
        result = instance.getGraphAliancas().numEdges();
        assertFalse("Espera false compatibilidade e acima de 1", booleanResult);
        assertEquals("Espera 4 edges pois a compatibilidade e acima de 1", expResult, result);

        compatibilidade = -0.1;
        booleanResult = instance.insertAlianca(p1, p2, relacao, compatibilidade);
        expResult = 4;
        result = instance.getGraphAliancas().numEdges();
        assertFalse("Espera false compatibilidade abaixo de 0", booleanResult);
        assertEquals("Espera 4 edges pois a compatibilidade e abaixo  de 0", expResult, result);
        Personagem PersonagemInvalida = null;
        compatibilidade = -0.1;
        booleanResult = instance.insertAlianca(PersonagemInvalida, p2, relacao, compatibilidade);
        expResult = 4;
        result = instance.getGraphAliancas().numEdges();
        assertFalse("Espera false personagem invalida", booleanResult);
        assertEquals("Espera 4 edges pois a personagem e invalida", expResult, result);
        booleanResult = instance.insertAlianca(p2, PersonagemInvalida, relacao, compatibilidade);
        expResult = 4;
        result = instance.getGraphAliancas().numEdges();
        assertFalse("Espera false personagem invalida", booleanResult);
        assertEquals("Espera 4 edges pois a personagem e invalida", expResult, result);

    }

    /**
     * Test of adicionarPersonagem method, of class MenuPrincipal.
     */
    @Test
    public void testAddPersonagem() {
        System.out.println("addPersonagem");
        MenuPrincipal instance = new MenuPrincipal();
        Personagem p1 = new Personagem("Teste 1", 455);
        Personagem p2 = new Personagem("Teste 2", 44);
        Personagem p3 = new Personagem("Teste 3", 90);
        Personagem p4 = new Personagem("Teste 4", 74);
        boolean result = instance.adicionarPersonagem(p1);
        assertTrue("Espera true, introduzui uma personagem nova \"p1\"", result);
        result = instance.adicionarPersonagem(p2);
        assertTrue("Espera true, introduzui uma personagem nova \"p2\"", result);
        result = instance.adicionarPersonagem(p3);
        assertTrue("Espera true, introduzui uma personagem nova \"p3\"", result);
        result = instance.adicionarPersonagem(p4);
        assertTrue("Espera true, introduzui uma personagem nova \"p4\"", result);
        result = instance.adicionarPersonagem(null);
        assertFalse("Espera false, introduzui uma personagem invalida", result);

    }

    /**
     * Test of adicionarLocalJogo method, of class MenuPrincipal.
     */
    @Test
    public void testAdicionarLocalJogo() {
        System.out.println("addLocal");
        Local l1 = new Local("Teste Local 1", 123);
        Local l2 = new Local("Teste Local 2", 456);
        Local l3 = new Local("Teste Local 3", 789);
        Local l4 = new Local("Teste Local 4", 159);
        MenuPrincipal instance = new MenuPrincipal();
        boolean result = instance.adicionarLocalJogo(l1);
        assertTrue(result);
        result = instance.adicionarLocalJogo(l2);
        assertTrue(result);
        result = instance.adicionarLocalJogo(l3);
        assertTrue(result);
        result = instance.adicionarLocalJogo(l4);
        assertTrue(result);
        result = instance.adicionarLocalJogo(null);
        assertFalse(result);
    }

    /**
     * Test of insertEstradaLocal method, of class MenuPrincipal.
     */
    @Test
    public void testInsertEstradaLocal() {
        System.out.println("insertEstradaLocal");
        Local l1 = new Local("Local 1", 123);
        Local l2 = new Local("Local 2", 456);
        Local l3 = new Local("Local 3", 789);
        Local l4 = new Local("Local 4", 159);
        MenuPrincipal instance = new MenuPrincipal();
        instance.adicionarLocalJogo(l1);
        instance.adicionarLocalJogo(l2);
        instance.adicionarLocalJogo(l3);
        instance.adicionarLocalJogo(l4);

        boolean result = instance.insertEstradaLocal(l1, l2, 45);
        assertTrue("Espera true, Inseriu uma nova estrada entre l1 e l2", result);
        result = instance.insertEstradaLocal(l1, l3, 45);
        assertTrue("Espera true, Inseriu uma nova estrada entre l1 e l3", result);
        result = instance.insertEstradaLocal(l1, l4, 45);
        assertTrue("Espera true, Inseriu uma nova estrada entre l1 e l4", result);
        result = instance.insertEstradaLocal(l2, l3, 45);
        assertTrue("Espera true, Inseriu uma nova estrada entre l2 e l3", result);
        result = instance.insertEstradaLocal(null, l3, 45);
        assertFalse("Espera false, inseriu um local invalido", result);
        result = instance.insertEstradaLocal(l2, null, 45);
        assertFalse("Espera false, inseriu um local invalido", result);

        result = instance.insertEstradaLocal(l3, l4, -45);
        assertFalse("Espera false, inseriu uma estrada invalida", result);

        result = instance.insertEstradaLocal(l3, l3, 5);
        assertFalse("Espera false, inseriu uma estrada no mesmo local", result);

        result = instance.insertEstradaLocal(l1, l2, 45);
        assertFalse("Espera false, inseriu uma estrada ja existente", result);

        result = instance.insertEstradaLocal(new Local("Novo Nao existente", 456), l2, 45);
        assertFalse("Espera false, pois o local nao existe no grafo", result);
        result = instance.insertEstradaLocal(l1, new Local("Novo Nao existente", 456), 45);
        assertFalse("Espera false, pois o local nao existe no grafo", result);
    }

    /**
     * Test of removeLocal method, of class MenuPrincipal.
     */
    @Test
    public void testRemoveLocal() {
        System.out.println("removeLocal");
        Local l1 = new Local("Local 1", 123);
        Local l2 = new Local("Local 2", 456);
        Local l3 = new Local("Local 3", 789);
        Local l4 = new Local("Local 4", 159);
        MenuPrincipal instance = new MenuPrincipal();
        instance.adicionarLocalJogo(l1);
        instance.adicionarLocalJogo(l2);
        instance.adicionarLocalJogo(l3);
        instance.adicionarLocalJogo(l4);

        boolean result = instance.removeLocal(l1);
        assertTrue("Espera true, para poder remover o vertice l1", result);
        int sizeResult = instance.getGameMap().numVertices();
        int sizeExpResult = 3;
        assertEquals("Espera 3, removeu o vertice l1, sobraram 3 vertices", sizeExpResult, sizeResult);

        result = instance.removeLocal(l2);
        assertTrue("Espera true, para poder remover o vertice l2", result);
        sizeResult = instance.getGameMap().numVertices();
        sizeExpResult = 2;
        assertEquals("Espera true, para poder remover o vertice l2", sizeExpResult, sizeResult);

        result = instance.removeLocal(new Local("LocalInvalido", 9000));
        assertFalse("Espera false, pois o local nao exite no grafo", result);
        sizeResult = instance.getGameMap().numVertices();
        sizeExpResult = 2;
        assertEquals("Esper 2, pois nao removeu o vertice", sizeExpResult, sizeResult);

        result = instance.removeLocal(null);
        assertFalse("Espera false, pois o local nao e valido", result);
        sizeResult = instance.getGameMap().numVertices();
        sizeExpResult = 2;
        assertEquals("Esper 2, pois nao removeu o vertice", sizeExpResult, sizeResult);

    }

    /**
     * Test of removePersonagem method, of class MenuPrincipal.
     */
    @Test
    public void testRemovePersonagem() {
        System.out.println("removePersonagem");
        Personagem p1 = new Personagem("Personagem teste 1", 500);
        Personagem p2 = new Personagem("Personagem teste 2", 400);
        Personagem p3 = new Personagem("Personagem teste 3", 300);
        Personagem p4 = new Personagem("Personagem teste 4", 200);
        Personagem p5 = new Personagem("Personagem teste 5", 100);
        MenuPrincipal instance = new MenuPrincipal();
        instance.adicionarPersonagem(p1);
        instance.adicionarPersonagem(p2);
        instance.adicionarPersonagem(p3);
        instance.adicionarPersonagem(p4);
        instance.adicionarPersonagem(p5);

        boolean result = instance.removePersonagem(p1);
        assertTrue("Espera true, removeu a personage p1", result);
        int sizeResult = instance.getGraphAliancas().numVertices();
        int sizeExpResult = 4;
        assertEquals("Espera 4 apos a remocao da personagem", sizeExpResult, sizeResult);

        result = instance.removePersonagem(p2);
        assertTrue("Espera true, removeu a personage p2", result);
        sizeResult = instance.getGraphAliancas().numVertices();
        sizeExpResult = 3;
        assertEquals("Espera 3 apos a remocao da personagem", sizeExpResult, sizeResult);

        result = instance.removePersonagem(null);
        assertFalse("Espera false, pois a personagem e null", result);
        sizeResult = instance.getGraphAliancas().numVertices();
        sizeExpResult = 3;
        assertEquals("Espera 3 pois nao removeu nenhuma personagem", sizeExpResult, sizeResult);

        result = instance.removePersonagem(new Personagem("PersonagemInvalida", 456));
        assertFalse("Espera false, pois a personagem nao esta no grafo", result);
        sizeResult = instance.getGraphAliancas().numVertices();
        sizeExpResult = 3;
        assertEquals("Espera 3 pois nao removeu nenhuma personagem", sizeExpResult, sizeResult);

    }
}
