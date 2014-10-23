import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EvaluationTest {
    private static final double EPSILON = 0.000001;

    @Test
    public void one() {
        Node n = new IntegerNode(1);
        assertEquals(1.0, n.evaluate(), EPSILON);
    }

    @Test
    public void onePlus2() {
        Node n1 = new IntegerNode(1);
        Node n2 = new IntegerNode(2);
        Node plus = new PlusNode(n1, n2);
        assertEquals(3.0, plus.evaluate(), EPSILON);
    }

    @Test
    public void oneMinus2() {
        Node n1 = new IntegerNode(1);
        Node n2 = new IntegerNode(2);
        Node minus = new MinusNode(n1, n2);
        assertEquals(-1.0, minus.evaluate(), EPSILON);
    }

    @Test
    public void twoTimes3() {
        Node n1 = new IntegerNode(2);
        Node n2 = new IntegerNode(3);
        Node times = new TimesNode(n1, n2);
        assertEquals(6.0, times.evaluate(), EPSILON);
    }

    @Test
    public void fourDividedBy2() {
        Node n1 = new IntegerNode(4);
        Node n2 = new IntegerNode(2);
        Node dividedBy = new DividedByNode(n1, n2);
        assertEquals(2.0, dividedBy.evaluate(), EPSILON);
    }

    @Test
    public void onePlus2Minus3() {
        Node n = new MinusNode(
                new PlusNode(new IntegerNode(1), new IntegerNode(2)),
                new IntegerNode(3)
        );
        assertEquals(0.0, n.evaluate(), EPSILON);
    }

    @Test
    public void oneMinus2Times3() {
        Node n = new MinusNode(
                new IntegerNode(1),
                new TimesNode(new IntegerNode(2), new IntegerNode(3))
        );
        assertEquals(-5.0, n.evaluate(), EPSILON);
    }

    @Test
    public void twoTimes9DividedBy3() {
        Node n = new TimesNode(
                new IntegerNode(2),
                new DividedByNode(new IntegerNode(9), new IntegerNode(3))
        );
        assertEquals(6.0, n.evaluate(), EPSILON);
    }

    @Test
    public void twelveDividedBy2Times3() {
        Node n = new DividedByNode(
                new IntegerNode(12),
                new TimesNode(new IntegerNode(2), new IntegerNode(3))
        );
        assertEquals(2.0, n.evaluate(), EPSILON);
    }
}
