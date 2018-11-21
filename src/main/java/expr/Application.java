package expr;

public class Application extends Expr {
    private Expr left;
    private Expr right;

    public Application(Expr left, Expr right)
    {
        this.left = left;
        this.right = right;
    }

    public Expr getLeft() {
        return left;
    }

    public void setLeft(Expr left) {
        this.left = left;
    }

    public Expr getRight() {
        return right;
    }

    public void setRight(Expr right) {
        this.right = right;
    }

    public void changeNode(final Expr previousExpr, final Expr newExpr) throws NoNodeFoundException {
        if(left == previousExpr) {
            left = newExpr;
        } else if(right == previousExpr) {
            right = newExpr;
        } else {
            throw new NoNodeFoundException();
        }
    }

    @Override
    public String toString() {
        return "@(" + left + " " + right + ")";
    }

    @Override
    public Expr clone() {
        return new Application(left.clone(), right.clone());
    }

    @Override
    public boolean equals(Expr expr) {
        return expr instanceof Application
                && ((Application) expr).getLeft().equals(this.getLeft())
                && ((Application) expr).getRight().equals(this.getRight());
    }
}
