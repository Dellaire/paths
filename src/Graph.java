import java.util.*;

public class Graph {

    private Set<Edge> edges = new HashSet<>();

    public Set<Edge> getEdges() {
        return edges;
    }

    public Graph addEdge(Edge firstEdge, Edge secondEdge) {

        Optional<Edge> existingFirstEdge = this.edges.stream()
                .filter(e -> e.equals(firstEdge))
                .findAny();

        if (existingFirstEdge.isPresent()) {
            existingFirstEdge.get().linkEdge(secondEdge);
        } else {
            firstEdge.linkEdge(secondEdge);
            this.edges.add(firstEdge);
        }

        Optional<Edge> existingSecondEdge = this.edges.stream()
                .filter(e -> e.equals(secondEdge))
                .findAny();

        if (existingSecondEdge.isPresent()) {
            existingSecondEdge.get().linkEdge(firstEdge);
        } else {
            secondEdge.linkEdge(firstEdge);
            this.edges.add(secondEdge);
        }

        return this;
    }
}
