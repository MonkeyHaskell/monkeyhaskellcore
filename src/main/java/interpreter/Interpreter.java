package interpreter;

import expr.*;
import expr.literal.Integer;
import expr.literal.Literal;
import interpreter.exceptions.UnknownBuiltInFunction;
import interpreter.exceptions.WrongTypeException;

public class Interpreter {
    public static Expr reduce(final Expr expr) throws Throwable {
        return reduce(expr, new Environment());
    }

    public static Expr reduceAll(final Expr expr) throws Throwable {
        Expr current;
        Expr reduced = expr;
        do {
            current = reduced;
            reduced = reduce(current, new Environment());
        } while(!current.equals(reduced));

        return reduced;
    }

    public static Expr reduce(Expr expr, Environment env) throws Throwable {
        Expr current = expr;

        // Look for leftmost outermost redex
        while(current instanceof Application) {
            env.getSpineStack().push((Application)current);
            current = ((Application) current).getLeft();
        }

        if(current instanceof Lambda) {
            // nothing to reduce since not applied
            if(env.getSpineStack().empty()) {
                return expr;
            } else {
            // reducing
                Lambda lambda = (Lambda)current;
                Expr converted = replace(lambda.getParameter().getValue(), lambda.getBody().clone(), env.getSpineStack().pop().getRight());
                return putBackInGraph(expr, converted, env);
            }
        } else if(current instanceof BuiltIn) {
            BuiltIn function = (BuiltIn) current;
            if(function.getName().equals("+")) {
                // not enough arguments, we stop there
                if(env.getSpineStack().size() < 2) {
                    return expr;
                } else {
                    Expr arg1 = env.getSpineStack().pop().getRight();
                    Expr arg2 = env.getSpineStack().pop().getRight();
                    Expr reducedArg1 = reduceAll(arg1);
                    Expr reducedArg2 = reduceAll(arg2);
                    if(reducedArg1 instanceof Integer && reducedArg2 instanceof Integer) {
                        Expr reduced = new Integer(((Integer) reducedArg1).getValue() + ((Integer) reducedArg2).getValue());
                        return putBackInGraph(expr, reduced, env);
                    } else {
                        throw new WrongTypeException();
                    }
                }
            } else if(function.getName().equals("head") || function.getName().equals("tail")) {
                if(env.getSpineStack().size() < 1) {
                    return expr;
                } else {
                    Expr arg = env.getSpineStack().pop().getRight();
                    Expr reducedArg = reduce(arg);
                    if(reducedArg instanceof Cons) {
                        return (function.getName().equals("head")) ? ((Cons) reducedArg).getHead() : ((Cons) reducedArg).getTail();
                    } else if(reducedArg instanceof Nil) {
                        return reducedArg;
                    } else {
                        throw new WrongTypeException();
                    }
                }
            } else {
                throw new UnknownBuiltInFunction();
            }
        } else {
            return expr;
        }
    }

    private static Expr putBackInGraph(Expr graph, Expr value, Environment env) {
        if(env.getSpineStack().empty()) {
            return value;
        } else {
            Application parent = env.getSpineStack().peek();
            parent.setLeft(value);
            return graph;
        }
    }

    private static Expr replace(String identifier, Expr body, Expr value) {
        if(body instanceof Identifier) {
            if(((Identifier) body).getValue().equals(identifier)) {
                return value;
            }
        }
        if(body instanceof Application) {
            Application application = (Application)body;
            application.setLeft(replace(identifier, application.getLeft(), value));
            application.setRight(replace(identifier, application.getRight(), value));
            return application;
        } else if(body instanceof Lambda) {
            Lambda lambda = (Lambda) body;
            if(!lambda.getParameter().getValue().equals(identifier)) {
                return replace(identifier, lambda.getBody().clone(), value);
            }
        }
        return body;
    }
}
