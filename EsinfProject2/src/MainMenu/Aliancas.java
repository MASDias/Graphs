package MainMenu;

import Entidades.AliancaMaisForte;
import Entidades.Conquista;
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

    public Conquista conquistarLocais(Personagem p, Local localActual, Local localDestino, AdjacencyMatrixGraph<Local, Double> gameMap) {
        if (localActual.equals(null)) {
            return null;
        }
        LinkedList<Local> caminhoConquistar = new LinkedList<>();
        //caminho de conquista mais facil (dificuldade de estradas e locai caso os mesmo tenham donos ou nao)
        AlgoritmosJogo.conquistaMaisFacil(gameMap, localActual, localDestino, caminhoConquistar, p);
        //forca actual do jogador
        double forcaJogador = p.getForca();
        //total de força necessaria para poder conquistar 
        double forcaNecessaria = 0;
        //Local inicial onde personagem esta alojada
        boolean sucesso = false;
        for (Local local : caminhoConquistar) {
            if (!localActual.equals(local)) {
                if (local.getDono() != p) {
                    forcaNecessaria += local.getDificuldade() + gameMap.getEdge(localActual, local);
                    localActual = local;
                } else {
                    forcaNecessaria += gameMap.getEdge(localActual, local);
                    localActual = local;
                }
            }
        }
        if (forcaJogador > forcaNecessaria) {
            sucesso = true;
        }
        Conquista conquista = new Conquista(sucesso, caminhoConquistar, forcaNecessaria);

        return conquista;
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
            if (caminhoAlianca.size() != 0) {
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
        int count = 0;
        for (Personagem p2 : aliancaIndirecta) {
            //faz o ciclo apartir da segunda personagem , pois agora tem acesso ao seu vertice adjacente (p1 neste caso)
            //podendo assim retirar o seu peso
            mediaCompatibilidade += aliancas.getEdge(p1, p2).getWeight();
            count++;
            //actualiza o vertice origem para o acesso ao Edge
            p1 = p2;
        }

        return mediaCompatibilidade / count;
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

    public AliancaMaisForte aliancaMaisForte() {
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
        AliancaMaisForte aliancaForte = new AliancaMaisForte(personagens, forca);
        return aliancaForte;
    }

    public Graph<Personagem, Boolean> novasAliancasPossiveis() {
        Graph<Personagem, Boolean> novasAliancas = aliancas.clone();
        for (Personagem p1 : novasAliancas.vertices()) {
            for (Personagem p2 : novasAliancas.vertices()) {
                if (!p1.equals(p2) && novasAliancas.getEdge(p1, p2) != null) {
                    for (Personagem p3 : novasAliancas.vertices()) {
                        if (!p1.equals(p3) && !p2.equals(p3) && novasAliancas.getEdge(p2, p3) != null && novasAliancas.getEdge(p1, p3) == null) {
                            double a1 = novasAliancas.getEdge(p2, p3).getWeight();
                            double a2 = novasAliancas.getEdge(p1, p2).getWeight();
                            double c = (a1 + a2) / 2;
                            novasAliancas.insertEdge(p1, p3, Boolean.TRUE, c);
                        }
                    }
                }
            }
        }
        return novasAliancas;
    }
}
