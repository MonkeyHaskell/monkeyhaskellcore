package expr;

public class Identifier extends Expr {
    private String value;

    public Identifier(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public Expr clone() {
        return new Identifier(value);
    }

    @Override
    public boolean equals(Expr expr) {
        return expr instanceof Identifier && ((Identifier)expr).getValue().equals(this.getValue());
    }
}
