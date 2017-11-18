package MainMenu;

import Entidades.AliancaMaisForte;
import Entidades.Conquista;
import Entidades.Local;
import Entidades.Personagem;
import graph.AdjacencyMatrixGraph;
import graph.AlgoritmosJogo;
import graphbase.Graph;
import java.util.LinkedList;
import java.util.Map;

public class MenuPrincipal {

    private AdjacencyMatrixGraph<Local, Double> gameMap;
    private Graph<Personagem, Boolean> graphAliancas;
    private Mapa mapa;
    private Aliancas alianca;

    public MenuPrincipal() {
        this.gameMap = new AdjacencyMatrixGraph<>();
        this.graphAliancas = new Graph<>(false);
        this.mapa = new Mapa(gameMap);
        this.alianca = new Aliancas(graphAliancas);
    }

    public boolean insertAlianca(Personagem p, Personagem p2, boolean relacao, double compatibilidade) {
        if (p != null && p2 != null) {
            if (graphAliancas.validVertex(p) && graphAliancas.validVertex(p2) && (compatibilidade >= 0 && compatibilidade <= 1)) {
                if (graphAliancas.getEdge(p, p2) == null) {
                    graphAliancas.insertEdge(p, p2, relacao, compatibilidade);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean adicionarPersonagem(Personagem p) {
        if (p != null) {
            graphAliancas.insertVertex(p);
            return true;
        }
        return false;
    }

    public boolean adicionarLocalJogo(Local local) {
        if (local != null) {
            gameMap.insertVertex(local);
            return true;
        }
        return false;
    }

    public boolean insertEstradaLocal(Local l, Local l2, double estrada) {
        if (l != null && l2 != null && !l.equals(l2)) {
            if (gameMap.checkVertex(l) && gameMap.checkVertex(l2)) {
                if (estrada > 0) {
                    if (gameMap.getEdge(l, l2) == null) {
                        gameMap.insertEdge(l, l2, estrada);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeLocal(Local l) {
        if (l != null && gameMap.checkVertex(l)) {
            gameMap.removeVertex(l);
            return true;
        }
        return false;
    }

    public boolean removePersonagem(Personagem p) {
        if (p != null && graphAliancas.validVertex(p)) {
            graphAliancas.removeVertex(p);
            return true;
        }
        return false;
    }

    public AdjacencyMatrixGraph<Local, Double> getGameMap() {
        return gameMap;
    }

    public Graph<Personagem, Boolean> getGraphAliancas() {
        return graphAliancas;
    }

    public Conquista locaisPersonagemPodeConsquistar(Personagem p, Local l) {
        if (!gameMap.checkVertex(l) || !graphAliancas.validVertex(p)) {
            return null;
        }
        return alianca.conquistarLocais(p, l, gameMap);
    }

    public LinkedList<Local> caminhoMaisProximo(Local l1, Local l2) {
        if (!gameMap.checkVertex(l1) || !gameMap.checkVertex(l2)) {
            return null;
        }
        return mapa.caminhoMaisFacil(l1, l2);
    }

    public LinkedList<Personagem> aliadosDePersonagem(Personagem p) {
        if (!graphAliancas.validVertex(p)) {
            return null;
        }
        return alianca.aliadosDePersonagem(p);
    }

    public boolean novaAlianca(Personagem A, Personagem B, boolean Relacao, double compatibilidade) {
        if (!graphAliancas.validVertex(A) || !graphAliancas.validVertex(B)) {
            return false;
        }
        if (compatibilidade < 0 || compatibilidade > 1) {
            return false;
        }
        return alianca.novaAlianca(A, B, Relacao, compatibilidade);
    }

    public AliancaMaisForte aliancaMaisForte() {
        AliancaMaisForte maisForte = alianca.aliancaMaisForte();
        if (maisForte == null) {
            return null;
        }
        return maisForte;
    }

    public Conquista verificarConquistaComAliado(Personagem p, Personagem aliado, Local localP, Local alvo) {
        if (!gameMap.checkVertex(localP) || !gameMap.checkVertex(alvo) || !graphAliancas.validVertex(p) || !graphAliancas.validVertex(aliado)) {
            return null;
        }
        AdjacencyMatrixGraph<Local, Double> mapWithoutAllies = gameMap.clone();
        for (Local l : mapWithoutAllies.vertices()) {
            if (aliado.equals(l.getDono())) {
                mapWithoutAllies.removeVertex(l);
            }
        }
        LinkedList<Local> locaisAConquistar = new LinkedList<>();
        AlgoritmosJogo.conquistaMaisFacil(mapWithoutAllies, localP, alvo, locaisAConquistar, p);
        double forcaAlianca = (p.getForca() + aliado.getForca()) * graphAliancas.getEdge(p, aliado).getWeight();
        double forcaNecessaria = 0;
        boolean sucesso = false;
        if(locaisAConquistar.isEmpty()) return new Conquista(Boolean.FALSE, new LinkedList<Local>(), 0,aliado);
        Local local2 = locaisAConquistar.getFirst();
        for (Local local : locaisAConquistar) {
            if (!local.equals(local2)) {
                if (local.getDono() != p) {
                    forcaNecessaria += local.getDificuldade() + mapWithoutAllies.getEdge(local, local2);
                    local2 = local;
                } else {
                    forcaNecessaria += mapWithoutAllies.getEdge(local, local2);
                    local2 = local;
                }
            }
        }
        if (forcaAlianca > forcaNecessaria) {
            sucesso = true;
        }

        return new Conquista(sucesso, locaisAConquistar, forcaNecessaria, aliado);
    }

    public Graph<Personagem, Boolean> novasAliancasPossiveis() {
        return alianca.novasAliancasPossiveis();
    }
}
