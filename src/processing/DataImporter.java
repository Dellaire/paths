package processing;

import data.Edge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.lang.Double.parseDouble;

public class DataImporter {

    public static List<Edge[]> readLines(String filePath) {

        java.nio.file.Path path = Paths.get(filePath);
        String input = "";
        try {
            input = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> lineStrings = Arrays.asList(input.split("\n"));
        List<String[]> edgeStrings = lineStrings.stream()
                .map(lineString -> lineString.split(" "))
                .toList();
        List<Edge[]> lines = edgeStrings.stream()
                .map(edgeString -> new Edge[]{
                        new Edge(parseDouble(edgeString[0]), parseDouble(edgeString[1])),
                        new Edge(parseDouble(edgeString[2]), parseDouble(edgeString[3]))
                })
                .toList();

        return lines;
    }
}
