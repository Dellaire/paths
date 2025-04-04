public record Vertex(Edge startEdge, Edge endEdge) {

    public boolean connects(Vertex otherVertex) {

        int collisions = 0;
        collisions = this.startEdge == otherVertex.startEdge ? collisions + 1 : collisions;
        collisions = this.startEdge == otherVertex.endEdge ? collisions + 1 : collisions;
        collisions = this.endEdge == otherVertex.startEdge ? collisions + 1 : collisions;
        collisions = this.endEdge == otherVertex.endEdge ? collisions + 1 : collisions;

        return collisions == 1;
    }
}
