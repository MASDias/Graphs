package MainMenu;

import Entidades.Local;
import Entidades.Personagem;
import graph.AdjacencyMatrixGraph;
import graph.AlgoritmosJogo;
import graphbase.Edge;
import graphbase.Graph;
import graphbase.GraphAlgorithms;
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
        AlgoritmosJogo.conquistaMaisFacil(gameMap, localActual, localDestino, lista);
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
            Graph<Personagem, Boolean> newGraph = geradorAliancasPublicas();
            LinkedList<Personagem> caminhoAlianca = new LinkedList<>();
            GraphAlgorithms.shortestPath(newGraph, a, b, caminhoAlianca);
            if (caminhoAlianca != null) {
                compatibilidade = geradorCompatibilidade(caminhoAlianca);
            }
            aliancas.insertEdge(a, b, relacao, compatibilidade);
            return true;
        }
        return false;
    }

    private double geradorCompatibilidade(LinkedList<Personagem> aliancaIndirecta) {
        double mediaCompatibilidade = 0;
        //remove a primeira personagem da lista
        Personagem p1 = aliancaIndirecta.removeFirst();
        if (!aliancaIndirecta.isEmpty()) {
            for (Personagem p2 : aliancaIndirecta) {
                //faz o ciclo apartir da segunda personagem , pois agora tem acesso ao seu vertice adjacente (p1 neste caso)
                //podendo assim retirar o seu peso
                mediaCompatibilidade += aliancas.getEdge(p1, p2).getWeight();
                //actualiza o vertice origem para o acesso ao Edge
                p1 = p2;
            }
        }
        return mediaCompatibilidade;
    }

    private Graph<Personagem, Boolean> geradorAliancasPublicas() {
        //criar um clone para refazer um novo graph de aliancas
        Graph<Personagem, Boolean> aliancasPublicas = aliancas.clone();
        for (Edge<Personagem, Boolean> edge : aliancasPublicas.edges()) {
            //se o Edge for false, ou seja, relacao da alianca nao for publica remove-se o mesmo
            if (!edge.getElement()) {
                aliancasPublicas.removeEdge(edge.getVOrig(), edge.getVDest());
            } else {
                //se houver relacao publica, ou seja true, mudamos o peso do ramo para 1 para poder calcular o "menor numero de personagens"
                //para obter a alianca
                edge.setWeight(1);
            }
        }
        return aliancasPublicas;
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
                    forca = (p.getForca() + p2.getForca()) * aliancas.getEdge(p, p2).getWeight();
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

}
