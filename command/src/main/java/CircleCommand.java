import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleCommand implements Command {
    @Override
    public Node getNode(double x, double y) {
        Circle circle = new Circle(10, Color.web("white", .3));
        circle.setCenterX(x);
        circle.setCenterY(y);
        return circle;
    }
}
