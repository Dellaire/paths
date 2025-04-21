package processing;

import data.Vertex;
import data.Line;
import data.Path;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PathCalculator {

    public static List<Path> calculatePaths(List<Line> lines) {

        List<Vertex> vertices = new ArrayList<>();
        lines.forEach(line -> {
            line.vertex1().linkVertex(line.vertex2());
            vertices.add(line.vertex1());
            line.vertex2().linkVertex(line.vertex1());
            vertices.add(line.vertex2());
        });

        List<Vertex> verticesOfMergedLines = mergeLinearPaths(vertices);

        List<Path> pathsAndSubPaths = new ArrayList<>(verticesOfMergedLines.stream()
                .map(Vertex::findLongestPathFromHere)
                .sorted(Comparator.comparing(Path::getLength).reversed())
                .distinct()
                .toList());

        List<Path> paths = eliminateSubPaths(pathsAndSubPaths);

        return paths;
    }

    private static List<Vertex> mergeLinearPaths(List<Vertex> vertices) {

        List<Vertex> verticesToBeRemoved = new ArrayList<>();
        vertices.forEach(vertex -> {

            List<Vertex> sameVertices = vertices.stream()
                    .filter(e -> e.getX().equals(vertex.getX()) && e.getY().equals(vertex.getY()))
                    .toList();

            if (sameVertices.size() == 2 && verticesToBeRemoved.stream().noneMatch(e -> e.toString().equals(vertex.toString()))) {
                mergeVertices(sameVertices.get(0), sameVertices.get(1));
                verticesToBeRemoved.add(sameVertices.get(1));
            }
        });

        List<Vertex> verticesOfMergedLines = new ArrayList<>(vertices);
        verticesToBeRemoved.forEach(verticesOfMergedLines::remove);

        return verticesOfMergedLines;
    }

    private static void mergeVertices(Vertex remainingVertex, Vertex obsoleteVertex) {

        remainingVertex.linkVertices(obsoleteVertex.getLinkedVertices());
        obsoleteVertex.getLinkedVertices().forEach(linkedVertex -> {
            linkedVertex.linkVertex(remainingVertex);
            linkedVertex.unlinkVertices(List.of(obsoleteVertex));
        });
    }

    private static List<Path> eliminateSubPaths(List<Path> pathsAndSubPaths) {

        List<Path> paths = new ArrayList<>(pathsAndSubPaths);

        int i = 1;
        while (i < paths.size()) {
            if (paths.get(i - 1).getVertices().contains(paths.get(i).getVertices().get(0))) {
                paths.remove(paths.get(i));
            } else {
                i++;
            }
        }

        return paths;
    }
}
