package data;

import java.util.*;
import java.util.stream.Collectors;

public class AmbiguousGraph {

    private final List<Edge> edges = new ArrayList<>();

    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    public Set<Line> getLines() {

        return this.edges.stream()
                .map(edge -> edge.getLinkedEdges().stream()
                        .map(linkedEdge -> new Line(edge, linkedEdge))
                        .toList())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public AmbiguousGraph addLine(Edge firstEdge, Edge secondEdge) {

        firstEdge.linkEdge(secondEdge);
        this.edges.add(firstEdge);

        secondEdge.linkEdge(firstEdge);
        this.edges.add(secondEdge);

        return this;
    }

    public AmbiguousGraph linkLinearPaths() {

        List<Edge> edgesToBeRemoved = new ArrayList<>();
        this.edges.forEach(edge -> {

            List<Edge> sameEdges = this.edges.stream()
                    .filter(edge::equals)
                    .toList();

            if (sameEdges.size() == 2) {

                sameEdges.get(0).linkEdges(sameEdges.get(1).getLinkedEdges());
                sameEdges.get(1).getLinkedEdges().forEach(linkedEdge -> {
                    linkedEdge.linkEdge(sameEdges.get(0));
                    linkedEdge.unlinkEdges(List.of(sameEdges.get(1)));
                });
                edgesToBeRemoved.add(sameEdges.get(1));
            }
        });

        this.edges.removeAll(edgesToBeRemoved);

        return this;
    }
}
