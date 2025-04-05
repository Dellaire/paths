package data;

import java.util.Objects;

public class Line {

    private final Edge edge1;
    private final Edge edge2;

    public Line(Edge edge1, Edge edge2) {
        this.edge1 = edge1;
        this.edge2 = edge2;
    }

    public Edge getEdge1() {
        return edge1;
    }

    public Edge getEdge2() {
        return edge2;
    }

    @Override
    public boolean equals(Object object) {

        if (object instanceof Line line) {
            return (this.edge1.equals(line.getEdge1()) && this.edge2.equals(line.getEdge2()))
                    || (this.edge1.equals(line.getEdge2()) && this.edge2.equals(line.getEdge1()));
        }

        return false;
    }

    @Override
    public String toString() {
        return this.edge1 + " -> " + this.edge2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.edge1.equals(this.edge2));
    }
}
