package ui;

import data.Vertex;
import data.Line;
import data.Path;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PathVisualization extends JFrame {

    private final static int WINDOW_SIZE = 1100;
    private final static int WINDOW_OFFSET = 50;
    private final static int CIRCLE_DIAMETER = 10;
    private final static int CIRCLE_OFFSET = CIRCLE_DIAMETER / 2;
    private final static int LABEL_OFFSET = 10;
    private final static List<Color> COLORS = List.of(Color.MAGENTA, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE, Color.RED);
    private final static int NUMBER_OF_COLOR = COLORS.size();

    private Graphics graphics;
    private final Map<Path, Color> paths = new LinkedHashMap<>();

    public PathVisualization(List<Path> paths) {

        this.setSize(WINDOW_SIZE, WINDOW_SIZE);
        this.setLocation(WINDOW_OFFSET, WINDOW_OFFSET);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        paths.forEach(path -> this.paths.put(path, COLORS.get(this.paths.size() % NUMBER_OF_COLOR)));
    }

    @Override
    public void paint(Graphics graphics) {

        super.paint(graphics);
        this.graphics = graphics;
        this.drawPaths();
        this.drawLabels();
    }

    private void drawPaths() {

        paths.forEach((path, color) -> {
            this.graphics.setColor(color);
            path.getLines().forEach(this::drawLine);
        });
    }

    private void drawLine(Line line) {

        this.graphics.drawLine(line.vertex1().getX().intValue(), line.vertex1().getY().intValue(),
                line.vertex2().getX().intValue(), line.vertex2().getY().intValue());

        this.graphics.fillOval(line.vertex1().getX().intValue() - CIRCLE_OFFSET,
                line.vertex1().getY().intValue() - CIRCLE_OFFSET, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        this.graphics.fillOval(line.vertex2().getX().intValue() - CIRCLE_OFFSET,
                line.vertex2().getY().intValue() - CIRCLE_OFFSET, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }

    private void drawLabels() {

        Set<Vertex> vertices = this.paths.keySet().stream()
                .flatMap(path -> path.getVertices().stream()).collect(Collectors.toSet());

        this.graphics.setColor(Color.BLACK);
        vertices.forEach(vertex ->
                this.graphics.drawString("%s,%s".formatted(vertex.getX().intValue(), vertex.getY().intValue()),
                        vertex.getX().intValue() + LABEL_OFFSET, vertex.getY().intValue()));
    }
}
