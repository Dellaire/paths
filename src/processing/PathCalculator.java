package processing;

import data.Edge;
import data.Line;
import data.Path;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PathCalculator {

    public static List<Path> calculatePaths(List<Line> lines) {

        List<Edge> edges = new ArrayList<>();
        lines.forEach(line -> {
            line.edge1().linkEdge(line.edge2());
            edges.add(line.edge1());
            line.edge2().linkEdge(line.edge1());
            edges.add(line.edge2());
        });

        mergeLinearPaths(edges);

        return edges.stream()
                .map(Edge::findLongestPathFromHere)
                .sorted(Comparator.comparing(Path::getLength).reversed())
                .distinct()
                .toList();
    }

    private static void mergeLinearPaths(List<Edge> edges) {

        List<Edge> edgesToBeRemoved = new ArrayList<>();
        edges.forEach(edge -> {

            List<Edge> sameEdges = edges.stream()
                    .filter(e -> e.getX().equals(edge.getX()) && e.getY().equals(edge.getY()))
                    .toList();

            if (sameEdges.size() == 2 && edgesToBeRemoved.stream().noneMatch(e -> e.toString().equals(edge.toString()))) {
                mergeEdges(sameEdges.get(0), sameEdges.get(1));
                edgesToBeRemoved.add(sameEdges.get(1));
            }
        });

        edgesToBeRemoved.forEach(edges::remove);
    }

    private static void mergeEdges(Edge remainingEdge, Edge obsoleteEdge) {

        remainingEdge.linkEdges(obsoleteEdge.getLinkedEdges());
        obsoleteEdge.getLinkedEdges().forEach(linkedEdge -> {
            linkedEdge.linkEdge(remainingEdge);
            linkedEdge.unlinkEdges(List.of(obsoleteEdge));
        });
    }
}
