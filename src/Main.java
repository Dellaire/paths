import data.DataImporter;
import data.Edge;
import data.Graph;
import data.Path;

import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        List<Edge[]> vertices = DataImporter.readVertices(args[0]);

        Graph graph = new Graph();
        vertices.forEach(vertex -> graph.addEdge(vertex[0], vertex[1]));

        Path path = graph.getEdges().stream().toList().get(0).findLongestPathFromHere(Set.of());
        graph.removeEdges(path.getEdges());

        System.out.println();
    }
}