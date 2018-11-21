package expr;

public class Nil extends Expr {
    @Override
    public String toString() {
        return "[]";
    }

    @Override
    public Expr clone() {
        return new Nil();
    }

    @Override
    public boolean equals(Expr expr) {
        return expr instanceof Nil;
    }
}
