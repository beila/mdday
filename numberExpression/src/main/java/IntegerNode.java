import java.util.Collection;
import java.util.Collections;

public class IntegerNode implements Node {
    private final int value;

    public IntegerNode(int v) {
        value = v;
    }

    @Override
    public double evaluate() {
        return (double) value;
    }

    @Override
    public Collection<Node> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerNode that = (IntegerNode) o;

        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
