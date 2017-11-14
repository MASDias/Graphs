package MainMenu;

import Entidades.Alianca;
import Entidades.Local;
import Entidades.Personagem;
import graph.AdjacencyMatrixGraph;
import java.util.LinkedList;
import java.util.Map;

public class MenuPrincipal {

    private AdjacencyMatrixGraph<Local, Double> gameMap;
    private AdjacencyMatrixGraph<Personagem, Alianca> aliancas;
    private Mapa mapa;
    private Aliancas alianca;

    public MenuPrincipal() {
        this.gameMap = new AdjacencyMatrixGraph<>();
        this.aliancas = new AdjacencyMatrixGraph<>();
        this.mapa = new Mapa(gameMap);
        this.alianca = new Aliancas(aliancas);

    }

    public AdjacencyMatrixGraph<Local, Double> getGameMap() {
        return gameMap;
    }

    public AdjacencyMatrixGraph<Personagem, Alianca> getAliancas() {
        return aliancas;
    }

    public  Map<LinkedList<Local>, Double> locaisPersonagemPodeConsquistar(Personagem p, Local l) {
        if (!gameMap.checkVertex(l) || !aliancas.checkVertex(p)) {
            return null;
        }
        return alianca.conquistarLocais(p, l, gameMap);
    }

    public LinkedList caminhoMaisProximo(Local l1, Local l2) {
        return mapa.caminhoMaisFacil(l1, l2);
    }
}
