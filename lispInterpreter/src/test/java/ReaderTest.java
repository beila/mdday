import ast.LispExpression;
import org.junit.Test;
import reader.LispReader;

import static org.junit.Assert.assertEquals;

public class ReaderTest {

    @Test
    public void testEmpty() {
        assertEquals(LispExpression.NIL, new LispReader().read(""));
    }

    @Test
    public void testSpace() {
        assertEquals(LispExpression.NIL, new LispReader().read(" "));
    }

    @Test
    public void testEmptyList() {
        assertEquals(LispExpression.NIL, new LispReader().read("()"));
    }

}
