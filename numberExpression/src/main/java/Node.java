import java.util.Collection;

public interface Node {
    double evaluate();

    Collection<Node> getChildren();
}
