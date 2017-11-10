package Ficheiro;

import Entidades.Alianca;
import Entidades.Local;
import Entidades.Personagem;
import MainMenu.MenuPrincipal;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeituraFicheiro {

//Constante para leitura de locais
    private final int LOCAL_LEITURA = 3;
    private final int LOCAL_LIGACAO_LEITURA = 3;

//Constante para leitura de personagens
    private final int PERSONAGEM_LEITURA = 2;
    private final int PERSONAGEM_ALIANCA_LEITURA = 4;

//Constante para leitura de locais
    private final int LOCAL = 0;
    private final int DIFICULDADE = 1;
    private final int DONO = 2;

//Constante para leitura de caminhos entre locais
    private final int LOCAL_A = 0;
    private final int LOCAL_B = 1;
    private final int DIFICULDADE_CAMINHO = 2;

//Constante para leitura das personagens
    private final int PERSONAGEM = 0;
    private final int PERSONAGEM_FORCA = 1;

//constante para alinaças entre personagens
    private final int PERSONAGEM_A = 0;
    private final int PERSONAGEM_B = 1;
    private final int TIPO_ALIANCA = 2;
    private final int COMPATIBILIDADE_LEITURA = 3;

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
                a = getPersonagem(nome_a, menu);
                b = getPersonagem(nome_b, menu);
                boolean publica = Boolean.parseBoolean(linha.split(SPLIT)[TIPO_ALIANCA]);
                double c = Double.parseDouble(linha.split(SPLIT)[COMPATIBILIDADE_LEITURA]);
                menu.getAliancas().insertEdge(a, b, new Alianca(publica,c));
            }
        }
    }

    private void lerLocais(String nomeFicheiro, MenuPrincipal menu) {
        ArrayList<String> lista = lerFicheiro(nomeFicheiro);
        String CAMINHOS = "CAMINHOS";

        boolean flag = false;
        for (String linha : lista) {
            if (linha.equals(CAMINHOS)) {
                flag = true;
            }
            if (flag) {
                if (linha.split(SPLIT).length > 1) {
                    String localA = linha.split(SPLIT)[LOCAL_A];
                    String localB = linha.split(SPLIT)[LOCAL_B];
                    Local a = getLocal(localA, menu);
                    Local b = getLocal(localB, menu);
                    double dificuldade = Double.parseDouble(linha.split(SPLIT)[DIFICULDADE_CAMINHO]);
                    System.out.println(dificuldade);
                    menu.getGameMap().insertEdge(a, b, dificuldade);
                }
            } else {
                if (linha.split(SPLIT).length > 1) {
                    String nomeDono = "";
                    String nome = linha.split(SPLIT)[LOCAL];
                    int dificuldade = Integer.parseInt(linha.split(SPLIT)[DIFICULDADE]);
                    Local l = new Local(nome, dificuldade);

                    if (linha.split(SPLIT).length == LOCAL_LIGACAO_LEITURA) {
                        nomeDono = linha.split(SPLIT)[DONO];
                    }
                    l.setDono(getPersonagem(nomeDono, menu));
                    menu.getGameMap().insertVertex(l);
                }
            }
        }
    }

    private Personagem getPersonagem(String nome, MenuPrincipal menu) {
        for (Personagem p : menu.getAliancas().vertices()) {
            if (p.getNome().equals(nome)) {
                return p;
            }
        }
        return null;
    }

    private Local getLocal(String nome, MenuPrincipal menu) {
        for (Local l : menu.getGameMap().vertices()) {
            if (l.getNome().equals(nome)) {
                return l;
            }
        }
        return null;
    }

}
