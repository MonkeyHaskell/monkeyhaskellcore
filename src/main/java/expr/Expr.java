package expr;

public abstract class Expr {
    public abstract Expr clone();
    public abstract boolean equals(Expr expr);
}
