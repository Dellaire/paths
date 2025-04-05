package data;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {

    private final Set<Edge> edges = new HashSet<>();

    public LinkedHashSet<Edge> getEdges() {
        return new LinkedHashSet<>(edges);
    }

    public Set<Line> getLines() {

        return this.edges.stream()
                .map(edge -> edge.getLinkedEdges().stream()
                        .map(linkedEdge -> new Line(edge, linkedEdge))
                        .toList())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public Graph addLine(Edge firstEdge, Edge secondEdge) {

        Edge existingFirstEdge = this.edges.stream()
                .filter(e -> e.equals(firstEdge))
                .findAny().orElse(firstEdge);

        Edge existingSecondEdge = this.edges.stream()
                .filter(e -> e.equals(secondEdge))
                .findAny().orElse(secondEdge);

        existingFirstEdge.linkEdge(existingSecondEdge);
        this.edges.add(existingFirstEdge);

        existingSecondEdge.linkEdge(existingFirstEdge);
        this.edges.add(existingSecondEdge);

        return this;
    }

    public Graph removeEdges(Collection<Edge> edgesToRemove) {

        this.edges.forEach(edge -> edge.unlinkEdges(edgesToRemove));
        this.edges.removeAll(edgesToRemove);
        return this;
    }

    /*public Path getLongestPathStartingWithEdge(Edge edge) {

    }*/
}
