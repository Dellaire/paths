package processing;

import data.Vertex;
import data.Line;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.lang.Double.parseDouble;

public class DataImporter {

    public static List<Line> readLines(String filePath) {

        java.nio.file.Path path = Paths.get(filePath);
        String input;
        try {
            input = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> lineStrings = Arrays.asList(input.split("\n"));
        List<String[]> vertexStrings = lineStrings.stream()
                .map(lineString -> lineString.split(" "))
                .toList();

        return vertexStrings.stream()
                .map(vertexString -> new Line(
                        new Vertex(parseDouble(vertexString[0]), parseDouble(vertexString[1])),
                        new Vertex(parseDouble(vertexString[2]), parseDouble(vertexString[3]))
                ))
                .toList();
    }
}
