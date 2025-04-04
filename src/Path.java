import java.util.List;

public record Path(List<Vertex> vertices) {

    public Path addLine(Vertex vertex) {
        this.vertices.add(vertex);
        return this;
    }
}
