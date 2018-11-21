package expr;

public class BuiltIn extends Expr {
    private String name;

    public BuiltIn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expr clone() {
        return new BuiltIn(name);
    }
}
