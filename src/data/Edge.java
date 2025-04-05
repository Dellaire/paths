package data;

import java.util.*;

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

    public Set<Edge> getLinkedEdges() {

        return new HashSet<>(this.linkedEdges.keySet());
    }

    public void linkEdge(Edge newEdge) {

        this.linkedEdges.put(newEdge, sqrt(pow(this.x - newEdge.x, 2) + pow(this.y - newEdge.y, 2)));
    }

    public void unlinkEdges(Collection<Edge> edgesToUnlink) {

        edgesToUnlink.forEach(edge -> this.linkedEdges.remove(edge));
    }

    public Path findLongestPathFromHere(Set<Edge> alreadyVisitedEdges) {

        return this.findLongestPathRecursive(alreadyVisitedEdges).addEdge(this);
    }

    public Path findLongestPathRecursive(Set<Edge> alreadyVisitedEdges) {

        Map<Edge, Double> remainingEdges = new HashMap<>(this.linkedEdges);
        alreadyVisitedEdges.forEach(remainingEdges::remove);

        if (remainingEdges.isEmpty()) {
            return new Path();
        }

        List<Path> paths = new ArrayList<>(remainingEdges.entrySet().stream()
                .map(edge -> {
                            Set<Edge> newAlreadyVisitedEdges = new HashSet<>(alreadyVisitedEdges);
                            newAlreadyVisitedEdges.add(this);
                            Path path = edge.getKey().findLongestPathRecursive(newAlreadyVisitedEdges);
                            path.addEdge(edge.getKey(), edge.getValue());
                            return path;
                        }
                ).toList());

        paths.sort(Comparator.comparing(Path::getLength).reversed());

        return paths.get(0);
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
