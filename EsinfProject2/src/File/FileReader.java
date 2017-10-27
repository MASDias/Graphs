package File;

import Entidades.Local;
import Entidades.Personagem;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

//Constante para leitura de locais
    private final int LOCAL = 0;
    private final int DIFICULDADE = 1;

//Constante para leitura de caminhos entre locais
    private final int LOCAL_PARTIDA = 0;
    private final int LOCAL_CHEGADA = 1;
    private final int CAMINHO = 2;

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

    public void populateGame(String nomeLocais, String nomePersonagens) {
        lerLocais(nomeLocais);
        lerPersonagens(nomePersonagens);
    }

    private void lerPersonagens(String nomeFicheiro) {
        ArrayList<String> lista = lerFicheiro(nomeFicheiro);
        for (String linha : lista) {
            if (linha.split(SPLIT).length == 2) {
                String nome = linha.split(SPLIT)[PERSONAGEM];
                int forca = Integer.parseInt(linha.split(SPLIT)[PERSONAGEM_FORCA]);
                Personagem p = new Personagem(nome, forca);
            }
        }
    }

    private void lerLocais(String nomeFicheiro) {
        ArrayList<String> lista = lerFicheiro(nomeFicheiro);
        for (String linha : lista) {
            if (linha.split(SPLIT).length == 2) {
                String nome = linha.split(SPLIT)[LOCAL];
                int dificuldade = Integer.parseInt(linha.split(SPLIT)[DIFICULDADE]);
                Local l = new Local(nome, dificuldade);
            }
        }
    }
}
