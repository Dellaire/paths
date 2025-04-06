package data;

import java.util.Objects;

public record Line(Edge edge1, Edge edge2) {

    @Override
    public boolean equals(Object object) {

        if (object instanceof Line line) {
            return (this.edge1.equals(line.edge1()) && this.edge2.equals(line.edge2()))
                    || (this.edge1.equals(line.edge2()) && this.edge2.equals(line.edge1()));
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
