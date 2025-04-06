package data;

import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Edge {

    private final Double x;
    private final Double y;
    private final Map<Edge, Double> linkedEdges = new HashMap<>();

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

    public void linkEdges(Collection<Edge> newEdges) {

        newEdges.forEach(newEdge ->
                this.linkedEdges.put(newEdge, sqrt(pow(this.x - newEdge.x, 2) + pow(this.y - newEdge.y, 2))));
    }

    public void unlinkEdges(Collection<Edge> edgesToUnlink) {

        edgesToUnlink.forEach(this.linkedEdges::remove);
    }

    public Path findLongestPathFromHere() {

        return this.findLongestPathRecursive(Set.of()).addEdge(this);
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

        paths.sort(Comparator.comparing(Path::getLength));

        return paths.get(0);
    }

    @Override
    public String toString() {
        return this.x.toString() + "," + this.y.toString();
    }
}
