package expr;

public class Lambda extends Expr {
    private Identifier arg;
    private Expr body;

    public Lambda(Expr body)
    {
        this(null, body);
    }
    public Lambda(Identifier arg, Expr body)
    {
        this.arg = arg;
        this.body = body;
    }

    public Identifier getParameter() {
        return arg;
    }
    public void setParameter(Identifier arg) {
        this.arg = arg;
    }
    public Expr getBody() {
        return body;
    }
    public void setBody(Expr body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "(\\" +arg + " . " + body + ')';
    }

    @Override
    public Expr clone() {
        return new Lambda(arg, body);
    }
}