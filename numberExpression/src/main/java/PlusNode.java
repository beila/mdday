public class PlusNode extends BinaryNode {
    public PlusNode(Node n1, Node n2) {
        super(n1, n2);
    }

    @Override
    public double evaluate() {
        return n1.evaluate() + n2.evaluate();
    }
}
