package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Path {

    private final List<Edge> edges = new ArrayList<>();
    private Double length = 0.0;

    public List<Edge> getEdges() {
        return edges;
    }

    public Double getLength() {
        return length;
    }

    public Path addEdge(Edge edge) {

        this.addEdge(edge, 0.0);
        return this;
    }

    public Path addEdge(Edge edge, Double length) {

        this.edges.add(0, edge);
        this.length += length;
        return this;
    }

    public List<Line> getLines() {

        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < edges.size() - 1; i++) {
            lines.add(new Line(this.edges.get(i), this.edges.get(i + 1)));
        }

        return lines;
    }

    @Override
    public boolean equals(Object object) {

        if (object instanceof Path path) {

            return this.edges.size() == path.getEdges().size()
                    && this.edges.containsAll(path.getEdges());
        }

        return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(new HashSet<>(this.edges));
    }
}
