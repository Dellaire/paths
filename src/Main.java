import data.*;
import data.AmbiguousGraph;
import processing.DataImporter;
import ui.LineVisualization;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        List<Edge[]> vertices = DataImporter.readLines(args[0]);
        AmbiguousGraph graph = new AmbiguousGraph();
        vertices.forEach(vertex -> graph.addLine(vertex[0], vertex[1]));
        graph.linkLinearPaths();

        List<Path> paths = graph.getEdges().stream()
                .map(edge -> edge.findLongestPathFromHere(Set.of()))
                .sorted(Comparator.comparing(Path::getLength).reversed())
                .distinct()
                .toList();

        Set<Line> lines = graph.getLines();

        LineVisualization visualization = new LineVisualization();
        visualization.setVisible(true);
        visualization.setLinesToVisualize(paths.get(0).getLines());
    }

    private void calculatePaths(String inputFilePath) {

        List<Edge[]> vertices = DataImporter.readLines(inputFilePath);
        Graph graph = new Graph();
        vertices.forEach(vertex -> graph.addLine(vertex[0], vertex[1]));

        Path path = graph.getEdges().stream().toList().get(0).findLongestPathFromHere(Set.of());
        graph.removeEdges(path.getEdges());

        System.out.println();
    }
}