package expr;

public class Cons extends Expr {
    private Expr head;
    private Expr tail;

    public Cons(Expr head, Expr tail) {
        this.head = head;
        this.tail = tail;
    }

    public Expr getHead() {
        return head;
    }

    public void setHead(Expr head) {
        this.head = head;
    }

    public Expr getTail() {
        return tail;
    }

    public void setTail(Expr tail) {
        this.tail = tail;
    }

    @Override
    public String toString() {
        return head + ":" + tail;
    }

    @Override
    public Expr clone() {
        return new Cons(head, tail);
    }
}
