import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Edge[]> verices = DataImporter.readVertices(args[0]);

        Graph graph = new Graph();
        verices.forEach(vertex -> graph.addEdge(vertex[0], vertex[1]));

        System.out.println();
    }
}