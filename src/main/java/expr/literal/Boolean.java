package expr.literal;

import expr.Expr;

public class Boolean extends Literal {

    private boolean value;

    public Boolean(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public java.lang.String toString() {
        return java.lang.String.valueOf(value);
    }

    @Override
    public Expr clone() {
        return new Boolean(value);
    }
}
