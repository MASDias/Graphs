package MainMenu;

import Entidades.Local;
import Entidades.Personagem;
import graph.AdjacencyMatrixGraph;

public class MenuPrincipal {

    AdjacencyMatrixGraph<Local, Local> gameMap;
    AdjacencyMatrixGraph<Personagem, Personagem> aliancas;
    Mapa mapa;
    Aliancas alianca;

    public MenuPrincipal() {
        this.gameMap = new AdjacencyMatrixGraph<>();
        this.aliancas = new AdjacencyMatrixGraph<>();
        this.mapa = new Mapa();
        this.alianca = new Aliancas();
        loadGameInfo();
    }

    public void loadGameInfo() {
        construcaoMapa();
        construcaoAliancas();
    }

    private void construcaoMapa() {
        mapa.adicionarLigacaoLocais(gameMap);
    }

    private void construcaoAliancas() {
        alianca.formarAlianca(aliancas);
    }

}
