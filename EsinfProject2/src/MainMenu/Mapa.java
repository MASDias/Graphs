package MainMenu;

import Entidades.Local;
import graph.AdjacencyMatrixGraph;
import static graph.EdgeAsDoubleGraphAlgorithms.shortestPath;
import java.util.LinkedList;

public class Mapa {
        AdjacencyMatrixGraph<Local, Double> map;

    public Mapa(AdjacencyMatrixGraph<Local, Double> mapa) {
        map = mapa;
    }
    
    public LinkedList<Local> caminhoMaisFacil(Local l1, Local l2){
        LinkedList<Local> path = new LinkedList<>();
        shortestPath(map, l1, l2, path);
        return path;
    }
    

}
