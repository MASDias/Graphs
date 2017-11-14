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
        shortestPath(gameMap, localActual, localDestino, caminhoConquistar);        //caminho de conquista mais facil (dificuldade de estradas)

        double forcaJogador = p.getForca();                                         //forca actual do jogador
        double forcaNecessaria = 0;                                                 //total de força necessaria para poder conquistar 
        caminhoConquistar.removeFirst();                                            //Local inicial onde personagem esta alojada
        boolean limite = false;                                                     //flag para verificar se o jogador chegou ao limite de conquistas

        for (Local local : caminhoConquistar) {
            forcaNecessaria += local.getDificuldade() + gameMap.getEdge(localActual, local);
            if (local.getDono()!=null) {
                forcaNecessaria += local.getDono().getForca();
            }
            if (!limite) {
                if (forcaJogador > forcaNecessaria) {
                    lista.add(local);
                } else {
                    limite = true;
                }
            }
            localActual = local;
        }
        locaisDificuldade.put(lista, forcaNecessaria);
        return locaisDificuldade;
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
