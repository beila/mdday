import javafx.scene.Node;

public interface Command {
    Node getNode(double x, double y);
}
