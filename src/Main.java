import data.*;
import ui.LineVisualization;

import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        List<Edge[]> vertices = DataImporter.readVertices(args[0]);
        Graph graph = new Graph();
        vertices.forEach(vertex -> graph.addLine(vertex[0], vertex[1]));
        Set<Line> lines = graph.getLines();

        LineVisualization visualization = new LineVisualization();
        visualization.setVisible(true);
        visualization.setLinesToVisualize(lines);
    }

    private void calculatePaths(String inputFilePath) {

        List<Edge[]> vertices = DataImporter.readVertices(inputFilePath);
        Graph graph = new Graph();
        vertices.forEach(vertex -> graph.addLine(vertex[0], vertex[1]));

        Path path = graph.getEdges().stream().toList().get(0).findLongestPathFromHere(Set.of());
        graph.removeEdges(path.getEdges());

        System.out.println();
    }
}