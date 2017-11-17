package MainMenu;

import Entidades.Conquista;
import Entidades.Local;
import Entidades.Personagem;
import graph.AdjacencyMatrixGraph;
import graph.AlgoritmosJogo;
import graphbase.Edge;
import graphbase.Graph;
import java.util.LinkedList;
import java.util.Map;

public class MenuPrincipal {

    private AdjacencyMatrixGraph<Local, Double> gameMap;
    private Graph<Personagem, Boolean> aliancas;
    private Mapa mapa;
    private Aliancas alianca;

    public MenuPrincipal() {
        this.gameMap = new AdjacencyMatrixGraph<>();
        this.aliancas = new Graph<>(false);
        this.mapa = new Mapa(gameMap);
        this.alianca = new Aliancas(aliancas);
    }

    public AdjacencyMatrixGraph<Local, Double> getGameMap() {
        return gameMap;
    }

    public Graph<Personagem, Boolean> getAliancas() {
        return aliancas;
    }

    public Map<LinkedList<Local>, Double> locaisPersonagemPodeConsquistar(Personagem p, Local l) {
        if (!gameMap.checkVertex(l) || !aliancas.validVertex(p)) {
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
        if (!aliancas.validVertex(p)) {
            return null;
        }
        return alianca.aliadosDePersonagem(p);
    }

    public boolean novaAlianca(Personagem A, Personagem B, boolean Relacao, double compatibilidade) {
        if (!aliancas.validVertex(A) || !aliancas.validVertex(B)) {
            return false;
        }
        if (compatibilidade < 0 || compatibilidade > 1) {
            return false;
        }
        return alianca.novaAlianca(A, B, Relacao, compatibilidade);
    }

    public Map<LinkedList<Personagem>, Double> aliancaMaisForte() {

        Map<LinkedList<Personagem>, Double> maisForte = alianca.aliancaMaisForte();

        return maisForte;
    }

    public Conquista verificarConquistaComAliado(Personagem p, Personagem aliado, Local localP, Local alvo) {
        if (!gameMap.checkVertex(localP) || !gameMap.checkVertex(localP)) {
            return null;
        }

        AdjacencyMatrixGraph<Local, Double> mapWithoutAllies = (AdjacencyMatrixGraph<Local, Double>) gameMap.clone();

        for (Local l : mapWithoutAllies.vertices()) {
            if (l.getDono().equals(aliado)) {
                mapWithoutAllies.removeVertex(l);
            }
        }

        LinkedList<Local> locaisAConquistar = new LinkedList<>();
        AlgoritmosJogo.conquistaMaisFacil(mapWithoutAllies, localP, alvo, locaisAConquistar);
        
        double forcaAlianca = (p.getForca() + aliado.getForca()) * aliancas.getEdge(p, aliado).getWeight();
        double forcaNecessaria = 0;
        boolean sucesso = false;

        Local local2 = locaisAConquistar.getFirst();

        for (Local local : locaisAConquistar) {
            if (!local.equals(local2)) {
                forcaNecessaria += local.getDificuldade() + mapWithoutAllies.getEdge(local, local2);
                local2 = local;
            }
        }
        
        if(forcaAlianca > forcaNecessaria){
            sucesso = true;
        }

        return new Conquista(sucesso, locaisAConquistar, forcaNecessaria, aliado);
    }

}
