package expr.literal;

import expr.Expr;

public class Integer extends Literal {
    private int value;

    public Integer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public java.lang.String toString() {
        return java.lang.String.valueOf(value);
    }

    @Override
    public Expr clone() {
        return new Integer(value);
    }

    @Override
    public boolean equals(Expr expr) {
        return expr instanceof Integer
                && ((Integer) expr).getValue() == this.getValue();
    }
}
