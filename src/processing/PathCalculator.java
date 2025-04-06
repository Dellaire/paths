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

        linkLinearPaths(edges);
        new Edge(227.0,882.0);

        return edges.stream()
                .map(Edge::findLongestPathFromHere)
                .sorted(Comparator.comparing(Path::getLength).reversed())
                .distinct()
                .toList();
    }

    private static void linkLinearPaths(List<Edge> edges) {

        List<Edge> edgesToBeRemoved = new ArrayList<>();
        edges.forEach(edge -> {

            List<Edge> sameEdges = edges.stream()
                    .filter(edge::equals)
                    .toList();

            if (sameEdges.size() == 2 && !edgesToBeRemoved.contains(edge)) {

                sameEdges.get(0).linkEdges(sameEdges.get(1).getLinkedEdges());
                sameEdges.get(1).getLinkedEdges().forEach(linkedEdge -> {
                    linkedEdge.linkEdge(sameEdges.get(0));
                    linkedEdge.unlinkEdges(List.of(sameEdges.get(1)));
                });
                edgesToBeRemoved.add(sameEdges.get(1));
            }
        });

        edgesToBeRemoved.forEach(edges::remove);
    }
}
