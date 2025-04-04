import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Edge {

    private Double x;
    private Double y;
    private Map<Edge, Double> linkedEdges = new HashMap<>();

    public Edge(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return this.x;
    }

    public Double getY() {
        return this.y;
    }

    public Map<Edge, Double> getLinkedEdges() {
        return new HashMap<>(this.linkedEdges);
    }

    public void linkEdge(Edge newEdge) {

        this.linkedEdges.put(newEdge, sqrt(pow(this.x - newEdge.x, 2) + pow(this.y - newEdge.y, 2)));
    }

    @Override
    public boolean equals(Object object) {

        if (object instanceof Edge edge) {
            return this.x.equals(edge.getX()) && this.y.equals(edge.getY());
        }

        return false;
    }

    @Override
    public String toString() {
        return this.x.toString() + "_" + this.y.toString();
    }
}
