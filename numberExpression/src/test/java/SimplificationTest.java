import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimplificationTest {
    private static final double EPSILON = 0.000001;

    @Test
    public void plusIdentity() {
        Node n1 = new IntegerNode(0);
        Node n2 = new IntegerNode(2);
        Node plus = new PlusNode(n1, n2);
        Simplifier s = new PlusIdentityRemover(plus);
        Node simplified = s.simplify();
        assertEquals(plus.evaluate(), simplified.evaluate(), EPSILON);

        NodeCounter plusCounter = new NodeCounter(plus);
        NodeCounter simplifiedCounter = new NodeCounter(simplified);
        // TODO
        assertTrue(simplifiedCounter.count() < plusCounter.count());
    }
}
