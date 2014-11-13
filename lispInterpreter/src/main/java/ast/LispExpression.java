package ast;

public interface LispExpression {
    static public LispExpression NIL = new Nil();

    static class Nil implements LispExpression {
    }
}
