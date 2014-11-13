package reader;

import ast.LispAtom;
import ast.LispExpression;
import ast.LispList;
import ast.LispNumber;
import org.javatuples.Pair;

public class LispReader {
    public LispExpression read(String s) {
        return readExpression(s).getValue0();
    }

    private Pair<? extends LispExpression, String> readExpression(String s) {
        s = s.trim();
        if (s.startsWith("(")) {
            return readList(s);
        }
        return readAtom(s);
    }

    private Pair<? extends LispAtom, String> readAtom(String s) {
        if (s.matches("^[0-9]")) {
            return readNumber(s);
        } // TODO identifier
        return null;
    }

    private Pair<LispNumber, String> readNumber(String s) {
        return null;    // TODO
    }

    private Pair<LispList, String> readList(String s) {
        s = s.substring(1, s.length()); // remove (
        LispList.Builder b = LispList.builder();
        do {
            Pair<? extends LispExpression, String> result = readExpression(s);
            b.add(result.getValue0());
            s = result.getValue1().trim();
        } while (!s.startsWith(")"));
        s = s.substring(1, s.length()); // remove )
        return new Pair<>(b.build(), s);
    }
}
