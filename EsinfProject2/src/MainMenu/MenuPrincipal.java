package MainMenu;

import Entidades.Alianca;
import Entidades.Local;
import Entidades.Personagem;
import File.LeituraFicheiro;
import graph.AdjacencyMatrixGraph;
import java.util.LinkedList;

public class MenuPrincipal {

    private AdjacencyMatrixGraph<Local, Double> gameMap;
    private AdjacencyMatrixGraph<Personagem, Alianca> aliancas;
    private Mapa mapa;
    private Aliancas alianca;

    public MenuPrincipal() {
        this.gameMap = new AdjacencyMatrixGraph<>();
        this.aliancas = new AdjacencyMatrixGraph<>();
        this.mapa = new Mapa(this);
        this.alianca = new Aliancas();
        loadGameInfo();
    }

    public AdjacencyMatrixGraph<Local, Double> getGameMap() {
        return gameMap;
    }

    public AdjacencyMatrixGraph<Personagem, Alianca> getAliancas() {
        return aliancas;
    }

    public void loadGameInfo() {
        LeituraFicheiro ficheiro = new LeituraFicheiro();
        construcaoAliancas();
    }

    private void construcaoAliancas() {
        alianca.formarAlianca(aliancas);
    }

    public LinkedList caminhoMaisProximo(Local l1, Local l2){
        return mapa.caminhoMaisFacil(l1, l2);
    }
}
