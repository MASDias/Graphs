package graph;

import Entidades.Local;
import java.util.LinkedList;

public class AlgoritmosJogo {

    private static void conquistaMaisFacil(AdjacencyMatrixGraph<Local, Double> graph, int sourceIdx, boolean[] knownVertices, int[] verticesIndex, double[] minDist) {
        minDist[sourceIdx] = 0;
        while (sourceIdx != -1) {
            knownVertices[sourceIdx] = true;
            for (Local vertice : graph.vertices) {
                int i = graph.toIndex(vertice);
                if (graph.privateGet(sourceIdx, i) != null) {
                    if (!knownVertices[i] && minDist[i] > minDist[sourceIdx] + vertice.getDificuldade() + graph.privateGet(sourceIdx, i)) {
                        minDist[i] = minDist[sourceIdx] + vertice.getDificuldade() + graph.privateGet(sourceIdx, i);
                        verticesIndex[i] = sourceIdx;
                    }
                }
            }

            Double min = Double.MAX_VALUE;
            sourceIdx = -1;
            for (int i = 0; i < graph.numVertices; i++) {
                if (!knownVertices[i] && minDist[i] < min) {
                    min = minDist[i];
                    sourceIdx = i;
                }
            }
        }
    }

    /**
     * Determine the shortest path between two vertices using Dijkstra's
     * algorithm
     *
     * @param graph Graph object
     * @param source Source vertex
     * @param dest Destination vertices
     * @param path Returns the vertices in the path (empty if no path)
     * @return minimum distance, -1 if vertices not in graph or no path
     *
     */
    public static double conquistaMaisFacil(AdjacencyMatrixGraph<Local, Double> graph, Local source, Local dest, LinkedList<Local> path) {
        int sourceIdx = graph.toIndex(source);
        if (sourceIdx == -1) {
            return -1;
        }
        int destIdx = graph.toIndex(dest);
        if (destIdx == -1) {
            return -1;
        }
        path.clear();
        boolean[] knownVertices = new boolean[graph.numVertices()];
        int[] verticesIndex = new int[graph.numVertices()];
        double[] minDist = new double[graph.numVertices()];
        for (int i = 0; i < graph.numVertices(); i++) {
            minDist[i] = Double.MAX_VALUE;
            verticesIndex[i] = -1;
        }
        AlgoritmosJogo.conquistaMaisFacil(graph, sourceIdx, knownVertices, verticesIndex, minDist);
        if (knownVertices[destIdx] == false) {
            return -1;
        }
        recreatePath(graph, sourceIdx, destIdx, verticesIndex, path);
        // recreatePath builds path in reverse order, so reverse
        LinkedList<Local> stack = new LinkedList<>();  //create a stack
        while (!path.isEmpty()) {
            stack.push(path.remove());
        }
        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return minDist[destIdx];
    }

    /**
     * Recreates the minimum path between two vertex, from the result of
     * Dikstra's algorithm
     *
     * @param graph Graph object
     * @param sourceIdx Source vertex
     * @param destIdx Destination vertices
     * @param verticesIndex index of vertices in the minimum path
     * @param Queue Vertices in the path (empty if no path)
     */
    private static void recreatePath(AdjacencyMatrixGraph<Local, Double> graph, int sourceIdx, int destIdx, int[] verticesIndex, LinkedList<Local> path) {
        path.add(graph.vertices.get(destIdx));
        if (sourceIdx != destIdx) {
            destIdx = verticesIndex[destIdx];
            recreatePath(graph, sourceIdx, destIdx, verticesIndex, path);
        }
    }

}
