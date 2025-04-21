package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Path {

    private final List<Vertex> vertices = new ArrayList<>();
    private Double length = 0.0;

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Double getLength() {
        return length;
    }

    public Path addVertex(Vertex vertex) {

        this.addVertex(vertex, 0.0);
        return this;
    }

    public Path addVertex(Vertex vertex, Double length) {

        this.vertices.add(0, vertex);
        this.length += length;
        return this;
    }

    public List<Line> getLines() {

        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < vertices.size() - 1; i++) {
            lines.add(new Line(this.vertices.get(i), this.vertices.get(i + 1)));
        }

        return lines;
    }

    @Override
    public String toString() {

        List<String> vertexStrings = this.vertices.stream().map(Vertex::toString).toList();

        return String.join(" -> ", vertexStrings);
    }

    @Override
    public boolean equals(Object object) {

        if (object instanceof Path path) {

            return this.vertices.size() == path.getVertices().size()
                    && this.vertices.containsAll(path.getVertices());
        }

        return false;
    }

    @Override
    public int hashCode() {

        return new HashSet<>(this.vertices).hashCode();
    }
}
