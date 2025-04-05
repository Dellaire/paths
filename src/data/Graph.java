package data;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Graph {

    private final Set<Edge> edges = new HashSet<>();

    public LinkedHashSet<Edge> getEdges() {
        return new LinkedHashSet<>(edges);
    }

    public Graph addEdge(Edge firstEdge, Edge secondEdge) {

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
