import expr.*;
import expr.literal.Integer;
import interpreter.Interpreter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Expr> steps = new ArrayList<>();

        Expr id = new Lambda(new Identifier("x"), new Identifier("x"));
        Expr add1 = new Lambda(new Identifier("x"), new Application(new Application(new BuiltIn("+"), new Identifier("x")), new Integer(1)));

        Expr test1 = new Application(add1, new Integer(1));
        Expr test2 = new Application(id, new Application(new BuiltIn("head"), new Cons(new Integer(4), new Cons(new Integer(3), new Nil()))));
        Expr test3 = new Application(id, new Application(new BuiltIn("tail"), new Cons(new Integer(4), new Cons(new Integer(3), new Nil()))));
        Expr test4 = new Application(add1, new Application(add1, new Integer(1)));

        reduceAndPrint(test1, 2);
        reduceAndPrint(test2, 2);
        reduceAndPrint(test3, 2);
        reduceAndPrint(test4, 1);
    }

    private static int count = 0;

    public static void reduceAndPrint(Expr expr, int numberOfSteps)
    {
        count++;
        List<Expr> steps = new ArrayList<>();

        if (numberOfSteps > 0)
        {
            try {
                steps.add(Interpreter.reduce(expr));
                for (int i = 1; i < numberOfSteps; i++)
                {
                    steps.add(Interpreter.reduce(steps.get(steps.size() - 1)));
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        System.out.println("======= Example " + count + " =======");
        System.out.println("         expr ::= " + expr);
        for (int i = 0; i < steps.size(); i++)
        {
        System.out.println("(step " + i + ") expr ::= " + steps.get(i));
        }
    }
}
