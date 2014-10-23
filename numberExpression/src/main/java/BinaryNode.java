import java.util.Arrays;
import java.util.Collection;

public abstract class BinaryNode implements Node {
    protected final Node n1;
    protected final Node n2;

    public BinaryNode(Node n1, Node n2) {
        this.n2 = n2;
        this.n1 = n1;
    }

    @Override
    public Collection<Node> getChildren() {
        return Arrays.asList(n1, n2);
    }
}
