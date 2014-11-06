import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AntTest {
    @Test
    public void test11() {
        Collection<String> strings = AntMain.run(1, 1);
        assertFalse(strings.isEmpty());
        Iterator<String> expectedIterator = Arrays.asList("1").iterator();
        for (String s: strings) {
            assertEquals(expectedIterator.next(), s);
        }
    }
}
