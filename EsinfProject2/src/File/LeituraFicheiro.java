package File;

import Entidades.Alianca;
import Entidades.Estrada;
import Entidades.Local;
import Entidades.Personagem;
import MainMenu.MenuPrincipal;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeituraFicheiro {

//Constante para leitura de locais
    private final int LOCAL_LEITURA = 2;
    private final int LOCAL_LIGACAO_LEITURA = 3;

//Constante para leitura de personagens
    private final int PERSONAGEM_LEITURA = 2;
    private final int PERSONAGEM_ALIANCA_LEITURA = 3;

//Constante para leitura de locais
    private final int LOCAL = 0;
    private final int DIFICULDADE = 1;

//Constante para leitura de caminhos entre locais
    private final int LOCAL_B = 0;
    private final int LOCAL_A = 1;
    private final int DIFICULDADE_CAMINHO = 2;

//Constante para leitura das personagens
    private final int PERSONAGEM = 0;
    private final int PERSONAGEM_FORCA = 1;

//constante para alinaças entre personagens
    private final int PERSONAGEM_A = 0;
    private final int PERSONAGEM_B = 1;
    private final int TIPO_ALIANCA = 2;

    private final String SPLIT = ",";

    private ArrayList<String> lerFicheiro(String nomeFicheiro) {
        ArrayList<String> linhas = new ArrayList<>();
        Scanner read = null;
        try {
            read = new Scanner(new java.io.FileReader(nomeFicheiro));
            while (read.hasNext()) {
                String next = read.next();
                linhas.add(next);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro nao encontrado");
        } finally {
            if (read != null) {
                read.close();
            }
        }
        return linhas;
    }

    public void populateGame(String nomeLocais, String nomePersonagens, MenuPrincipal menu) {
        lerLocais(nomeLocais, menu);
        lerPersonagens(nomePersonagens, menu);
    }

    private void lerPersonagens(String nomeFicheiro, MenuPrincipal menu) {
        ArrayList<String> lista = lerFicheiro(nomeFicheiro);
        for (String linha : lista) {
            if (linha.split(SPLIT).length == PERSONAGEM_LEITURA) {
                String nome = linha.split(SPLIT)[PERSONAGEM];
                int forca = Integer.parseInt(linha.split(SPLIT)[PERSONAGEM_FORCA]);
                Personagem p = new Personagem(nome, forca);
                menu.getAliancas().insertVertex(p);
            }
            if (linha.split(SPLIT).length == PERSONAGEM_ALIANCA_LEITURA) {
                Personagem a = null;
                Personagem b = null;
                String nome_a = linha.split(SPLIT)[PERSONAGEM_A];
                String nome_b = linha.split(SPLIT)[PERSONAGEM_B];
                for (Personagem p : menu.getAliancas().vertices()) {
                    if (p.getNome().equals(nome_a)) {
                        a = p;
                    }
                    if (p.getNome().equals(nome_b)) {
                        b = p;
                    }
                    if (!a.equals(null) && !b.equals(null)) {
                        break;
                    }
                }
                boolean publica = Boolean.getBoolean(linha.split(SPLIT)[TIPO_ALIANCA]);
                menu.getAliancas().insertEdge(a, b, new Alianca(publica));
            }
        }
    }

    private void lerLocais(String nomeFicheiro, MenuPrincipal menu) {
        ArrayList<String> lista = lerFicheiro(nomeFicheiro);
        for (String linha : lista) {
            if (linha.split(SPLIT).length == LOCAL_LEITURA) {
                String nome = linha.split(SPLIT)[LOCAL];
                int dificuldade = Integer.parseInt(linha.split(SPLIT)[DIFICULDADE]);
                Local l = new Local(nome, dificuldade);
                menu.getGameMap().insertVertex(l);
            }
            if (linha.split(SPLIT).length == LOCAL_LIGACAO_LEITURA) {
                Local a = null;
                Local b = null;
                String localA = linha.split(SPLIT)[LOCAL_A];
                String localB = linha.split(SPLIT)[LOCAL_B];
                for (Local l : menu.getGameMap().vertices()) {
                    if (l.getNome().equals(localA)) {
                        a = l;
                    }
                    if (l.getNome().equals(localB)) {
                        b = l;
                    }
                    if (!l.equals(null) && !l.equals(null)) {
                        break;
                    }
                }
                float dificuldade = Float.parseFloat(linha.split(SPLIT)[DIFICULDADE_CAMINHO]);
                menu.getGameMap().insertEdge(a, b, new Estrada(dificuldade));
            }
        }
    }
}
