package MainMenu;

import Entidades.Local;
import Entidades.Personagem;
import graph.AdjacencyMatrixGraph;
import static graph.EdgeAsDoubleGraphAlgorithms.shortestPath;
import graphbase.Graph;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Aliancas {

    private Graph<Personagem, Boolean> aliancas;

    public Aliancas(Graph<Personagem, Boolean> aliancas) {
        this.aliancas = aliancas;
    }

    public Map<LinkedList<Local>, Double> conquistarLocais(Personagem p, Local localDestino, AdjacencyMatrixGraph<Local, Double> gameMap) {
        Local localActual = getDonoLocal(p, gameMap);
        if (localActual.equals(null)) {
            return null;
        }
        Map<LinkedList<Local>, Double> locaisDificuldade = new HashMap<>();
        LinkedList<Local> lista = new LinkedList<>();
        LinkedList<Local> caminhoConquistar = new LinkedList<>();
        //caminho de conquista mais facil (dificuldade de estradas)
        shortestPath(gameMap, localActual, localDestino, caminhoConquistar);
        //forca actual do jogador
        double forcaJogador = p.getForca();
        //total de força necessaria para poder conquistar 
        double forcaNecessaria = 0;
        //Local inicial onde personagem esta alojada
        caminhoConquistar.removeFirst();
        //flag para verificar se o jogador chegou ao limite de conquista
        boolean limite = false;
        for (Local local : caminhoConquistar) {
            forcaNecessaria += local.getDificuldade() + gameMap.getEdge(localActual, local);
            if (local.getDono() != null) {
                forcaNecessaria += local.getDono().getForca();
            }
            if (!limite) {
                if (forcaJogador > forcaNecessaria) {
                    lista.add(local);
                } else {
                    //se o a forca nao for suficiente, o jogador chegou ao limite
                    limite = true;
                }
            }
            localActual = local;
        }
        locaisDificuldade.put(lista, forcaNecessaria);
        return locaisDificuldade;
    }

    public LinkedList<Personagem> aliadosDePersonagem(Personagem p) {
        LinkedList<Personagem> listaAliados = new LinkedList<>();
        for (Personagem personagem : aliancas.adjVertices(p)) {
            listaAliados.add(personagem);
        }
        return listaAliados;
    }

    public boolean novaAlianca(Personagem a, Personagem b, boolean relacao, double compatibilidade) {
        if (aliancas.getEdge(a, b) == null && !a.equals(b)) {
            aliancas.insertEdge(a, b, relacao, compatibilidade);
            return true;
        }
        return false;
    }

    private Local getDonoLocal(Personagem p, AdjacencyMatrixGraph<Local, Double> gameMap) {
        for (Local local : gameMap.vertices()) {
            if (p.equals(local.getDono())) {
                return local;
            }
        }
        return null;
    }

    public Map<LinkedList<Personagem>, Double> aliancaMaisForte() {

        Map<LinkedList<Personagem>, Double> mapaPersonagensForca = new HashMap<>();
        LinkedList<Personagem> personagens = new LinkedList<>();
        double forca = 0;
        double maisForte = 0;
        for (Personagem p : aliancas.vertices()) {
            for (Personagem p2 : aliancas.adjVertices(p)) {
                if (aliancas.getEdge(p, p2) != null) {
//                    forca = (p.getForca() + p2.getForca()) * aliancas.getEdge(p, p2);
                    if (forca > maisForte) {
                        maisForte = forca;
                        personagens.clear();
                        personagens.add(p);
                        personagens.add(p2);
                    }
                }
            }
        }
        mapaPersonagensForca.put(personagens, maisForte);
        return mapaPersonagensForca;
    }

    public Graph<Personagem, Boolean> aliancasPossiveis(Graph<Personagem, Boolean> graph) {
        Graph<Personagem, Boolean> clone = graph.clone();
        double compatibilidade = 0.5;
        boolean relacao = true;
        for (Personagem p : clone.allkeyVerts()) {
            for (Personagem p2 : clone.allkeyVerts()) {
                if (clone.getEdge(p, p2)!= null && !p.equals(p2)) {
                    for (Personagem p3 : clone.allkeyVerts()) {
                        if (!p.equals(p3) && !p2.equals(p3) && clone.getEdge(p, p3)!= null) {
                            clone.insertEdge(p, p3, relacao, compatibilidade);
                        }
                    }
                }
            }
        }
        return clone;
    }
}
