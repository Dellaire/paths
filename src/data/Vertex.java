package data;

import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vertex {

    private final Double x;
    private final Double y;
    private final Map<Vertex, Double> linkedVertices = new HashMap<>();

    public Vertex(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return this.x;
    }

    public Double getY() {
        return this.y;
    }

    public Set<Vertex> getLinkedVertices() {

        return new HashSet<>(this.linkedVertices.keySet());
    }

    public void linkVertex(Vertex newVertex) {

        this.linkedVertices.put(newVertex, sqrt(pow(this.x - newVertex.x, 2) + pow(this.y - newVertex.y, 2)));
    }

    public void linkVertices(Collection<Vertex> newVertices) {

        newVertices.forEach(newVertex ->
                this.linkedVertices.put(newVertex, sqrt(pow(this.x - newVertex.x, 2) + pow(this.y - newVertex.y, 2))));
    }

    public void unlinkVertices(Collection<Vertex> verticessToUnlink) {

        verticessToUnlink.forEach(this.linkedVertices::remove);
    }

    public Path findLongestPathFromHere() {

        return this.findLongestPathRecursive(Set.of()).addVertex(this);
    }

    public Path findLongestPathRecursive(Set<Vertex> alreadyVisitedVertices) {

        Map<Vertex, Double> remainingVertices = new HashMap<>(this.linkedVertices);
        alreadyVisitedVertices.forEach(remainingVertices::remove);

        if (remainingVertices.isEmpty()) {
            return new Path();
        }

        List<Path> paths = new ArrayList<>(remainingVertices.entrySet().stream()
                .map(vertex -> {
                            Set<Vertex> newAlreadyVisitedVertices = new HashSet<>(alreadyVisitedVertices);
                            newAlreadyVisitedVertices.add(this);
                            Path path = vertex.getKey().findLongestPathRecursive(newAlreadyVisitedVertices);
                            path.addVertex(vertex.getKey(), vertex.getValue());
                            return path;
                        }
                ).toList());

        paths.sort(Comparator.comparing(Path::getLength).reversed());

        return paths.get(0);
    }

    @Override
    public String toString() {
        return this.x.toString() + "," + this.y.toString();
    }
}
