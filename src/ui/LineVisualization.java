package ui;

import data.Line;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LineVisualization extends JFrame {

    private final static int CIRCLE_DIAMETER = 10;
    private final static int CIRCLE_OFFSET = CIRCLE_DIAMETER / 2;

    private Graphics graphics;
    private final List<Line> lines = new ArrayList<>();

    public LineVisualization() {

        this.setSize(1000, 1000);
        this.setLocation(50, 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics graphics) {

        super.paint(graphics);
        this.graphics = graphics;
        this.drawLines();
    }

    public void setLinesToVisualize(Collection<Line> lines) {

        this.lines.addAll(lines);
    }

    private void drawLines() {

        lines.forEach(line -> {

            this.graphics.drawLine(line.getEdge1().getX().intValue(), line.getEdge1().getY().intValue(),
                    line.getEdge2().getX().intValue(), line.getEdge2().getY().intValue());

            this.graphics.fillOval(line.getEdge1().getX().intValue() - CIRCLE_OFFSET, line.getEdge1().getY().intValue() - CIRCLE_OFFSET, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
            this.graphics.fillOval(line.getEdge2().getX().intValue() - CIRCLE_OFFSET, line.getEdge2().getY().intValue() - CIRCLE_OFFSET, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        });
    }
}
