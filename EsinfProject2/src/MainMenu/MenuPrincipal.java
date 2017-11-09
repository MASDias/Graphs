package MainMenu;

import Entidades.Alianca;
import Entidades.Estrada;
import Entidades.Local;
import Entidades.Personagem;
import File.LeituraFicheiro;
import graph.AdjacencyMatrixGraph;

public class MenuPrincipal {

    private AdjacencyMatrixGraph<Local, Estrada> gameMap;
    private AdjacencyMatrixGraph<Personagem, Alianca> aliancas;
    private Mapa mapa;
    private Aliancas alianca;

    public MenuPrincipal() {
        this.gameMap = new AdjacencyMatrixGraph<>();
        this.aliancas = new AdjacencyMatrixGraph<>();
        this.mapa = new Mapa();
        this.alianca = new Aliancas();
        loadGameInfo();
    }

    public AdjacencyMatrixGraph<Local, Estrada> getGameMap() {
        return gameMap;
    }

    public AdjacencyMatrixGraph<Personagem, Alianca> getAliancas() {
        return aliancas;
    }

    public void loadGameInfo() {
        LeituraFicheiro ficheiro = new LeituraFicheiro();
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
