import expr.*;
import expr.literal.Integer;
import interpreter.Interpreter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int exampleCounter = 0;

    public static void main(String[] args) {
        Expr id = new Lambda(new Identifier("x"), new Identifier("x"));
        Expr add1 = new Lambda(new Identifier("x"), new Application(new Application(new BuiltIn("+"), new Identifier("x")), new Integer(1)));

        Expr test1 = new Application(add1, new Integer(1));
        Expr test2 = new Application(id, new Application(new BuiltIn("head"), new Cons(new Integer(4), new Cons(new Integer(3), new Nil()))));
        Expr test3 = new Application(id, new Application(new BuiltIn("tail"), new Cons(new Integer(4), new Cons(new Integer(3), new Nil()))));
        Expr test4 = new Application(add1, new Application(add1, new Integer(1)));

        reduceAndPrint(test1);
        reduceAndPrint(test2);
        reduceAndPrint(test3);
        reduceAndPrint(test4);
    }

    private static void reduceAndPrint(final Expr expr) {
        try {
            exampleCounter++;
            List<Expr> steps = new ArrayList<>();
            Expr reduced = expr;
            Expr current;

            do {
                current = reduced;
                steps.add(current);
                reduced = Interpreter.reduce(current);
            } while (!current.equals(reduced));

            System.out.println("======= Example " + exampleCounter + " =======");
            for (int i = 0; i < steps.size(); i++) {
                System.out.println("(step " + i + ") expr ::= " + steps.get(i));
            }
        } catch(Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
