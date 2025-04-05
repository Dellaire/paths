package data;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Edge> edges = new ArrayList<>();
    private Double length = 0.0;

    public Path addEdge(Edge edge) {

        this.addEdge(edge, 0.0);
        return this;
    }

    public Path addEdge(Edge edge, Double length) {

        this.edges.add(0, edge);
        this.length += length;
        return this;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Double getLength() {
        return length;
    }
}
