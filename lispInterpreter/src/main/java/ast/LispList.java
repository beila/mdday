package ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LispList implements LispExpression {
    private final List<LispExpression> elements;

    private LispList(List<LispExpression> elements) {
        this.elements = Collections.unmodifiableList(elements);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<LispExpression> elements = new ArrayList<>();

        public void add(LispExpression element) {
            elements.add(element);
        }

        public LispList build() {
            return new LispList(elements);
        }
    }
}
