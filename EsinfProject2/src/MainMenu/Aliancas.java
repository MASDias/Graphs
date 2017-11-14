package MainMenu;

import Entidades.Alianca;
import Entidades.Local;
import Entidades.Personagem;
import graph.AdjacencyMatrixGraph;
import static graph.EdgeAsDoubleGraphAlgorithms.shortestPath;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Aliancas {

    private AdjacencyMatrixGraph<Personagem, Alianca> aliancas;

    public Aliancas(AdjacencyMatrixGraph<Personagem, Alianca> aliancas) {
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
        for (Personagem personagem : aliancas.directConnections(p)) {
            listaAliados.add(personagem);
        }
        return listaAliados;
    }

    public boolean  novalAlianca(Personagem a, Personagem b, boolean relacao, double compatibilidade) {
        if (aliancas.getEdge(a, b)==null && !a.equals(b)) {
            aliancas.insertEdge(a, b, new Alianca(relacao, compatibilidade));
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
}
