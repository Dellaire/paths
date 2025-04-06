package ui;

import data.Line;
import data.Path;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PathVisualization extends JFrame {

    private final static int Y_OFFSET = 0;
    private final static int WINDOW_SIZE = 1000;
    private final static int WINDOW_OFFSET = 50;
    private final static int CIRCLE_DIAMETER = 10;
    private final static int CIRCLE_OFFSET = -CIRCLE_DIAMETER / 2;
    private final static int CIRCLE_Y_OFFSET = Y_OFFSET > 0 ? -CIRCLE_OFFSET : CIRCLE_OFFSET;
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
    }

    private void drawPaths() {

        paths.forEach((path, color) -> {
            this.graphics.setColor(color);
            path.getLines().forEach(this::drawLine);
        });
    }

    private void drawLine(Line line) {

        this.graphics.drawLine(this.getX(line.edge1().getX().intValue()), this.getY(line.edge1().getY().intValue()),
                this.getX(line.edge2().getX().intValue()), this.getY(line.edge2().getY().intValue()));

        this.graphics.fillOval(this.getX(line.edge1().getX().intValue() + CIRCLE_OFFSET),
                this.getY(line.edge1().getY().intValue() + CIRCLE_Y_OFFSET), CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        this.graphics.fillOval(this.getX(line.edge2().getX().intValue() + CIRCLE_OFFSET),
                this.getY(line.edge2().getY().intValue() + CIRCLE_Y_OFFSET), CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }

    private int getX(int x) {
        return x;
    }

    private int getY(int y) {
        return y;
    }
}
