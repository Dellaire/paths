package data;

public record Line(Vertex vertex1, Vertex vertex2) {

    @Override
    public boolean equals(Object object) {

        if (object instanceof Line line) {
            return (this.vertex1.equals(line.vertex1()) && this.vertex2.equals(line.vertex2()))
                    || (this.vertex1.equals(line.vertex2()) && this.vertex2.equals(line.vertex1()));
        }

        return false;
    }

    @Override
    public String toString() {
        return this.vertex1 + " -> " + this.vertex2;
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 31 * hash + this.vertex1.hashCode();
        hash = 31 * hash + this.vertex2.hashCode();

        return hash;
    }
}
