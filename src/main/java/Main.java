import expr.*;
import expr.literal.Integer;
import interpreter.Interpreter;

public class Main {
    public static void main(String[] args) {

        Expr step1 = null;
        Expr step2 = null;
        Expr add1 = new Lambda(new Identifier("x"), new Application(new Application(new BuiltIn("+"), new Identifier("x")), new Integer(1)));
        Expr test1 = new Application(add1, new Integer(1));


        try {
            step1 = Interpreter.reduce(test1);
            step2 = Interpreter.reduce(step1);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("======= Example 1 =======");
        System.out.println("           add1 ::= " + add1);
        System.out.println("          test1 ::= " + test1);
        System.out.println("(reduced) test1 ::= " + step1);
        System.out.println("(reduced) test1 ::= " + step2);
    }
}
