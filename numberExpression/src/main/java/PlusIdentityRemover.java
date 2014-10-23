import java.util.stream.Stream;

public class PlusIdentityRemover implements Simplifier {
    public static final IntegerNode IDENTITY = new IntegerNode(0);
    private final Node node;

    public PlusIdentityRemover(Node node) {
        this.node = node;
    }

    @Override
    public Node simplify() {
        Stream<Node> effectiveNodes = node.getChildren().stream().filter(n -> !IDENTITY.equals(n));
        switch ((int) effectiveNodes.spliterator().estimateSize()) {
            case 0: return IDENTITY;
            case 1: return effectiveNodes.findAny().get();
            default: return node;
        }
    }
}
