import data.Line;
import data.Path;
import processing.DataImporter;
import processing.PathCalculator;
import ui.PathVisualization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Line> lines = DataImporter.readLines(args[0]);
        List<Path> paths = PathCalculator.calculatePaths(lines);

        System.out.println();
        paths.forEach(path -> System.out.printf("Path with length %s: %s%n", path.getLength(), path));

        List<Path> reversedPaths = new ArrayList<>(paths);
        Collections.reverse(reversedPaths);
        new PathVisualization(reversedPaths);
    }
}