package expr;

import expr.literal.Literal;

import java.util.List;
import java.util.function.Function;

public class BuiltIn extends Expr {
    private String name;
    private int arity;
    private Function<List<Expr>, Literal> function;

    public BuiltIn(String name) {
        this.name = name;
    }

    public BuiltIn(String name, int arity, Function function) {
        this.name = name;
        this.arity = arity;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public int getArity() {
        return arity;
    }

    public Function<List<Expr>, Literal> getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expr clone() {
        return new BuiltIn(name, arity, function);
    }

    @Override
    public boolean equals(Expr expr) {
        return expr instanceof BuiltIn
                && ((BuiltIn) expr).getName().equals(this.getName());
    }
}
