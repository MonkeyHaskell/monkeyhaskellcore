package interpreter;

import expr.*;
import expr.literal.Integer;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Env
{
    private Stack<Application> spineStack;
    private Map<String, Expr> lookupTable;

    public Env()
    {
        this.spineStack = new Stack<>();
        this.lookupTable = new HashMap<>();
    }

    public Env(Stack<Application> spineStack, Map<String, Expr> lookupTable) {
        this.spineStack = spineStack;
        this.lookupTable = lookupTable;
    }

    public Stack<Application> getSpineStack() {
        return spineStack;
    }

    public Map<String, Expr> getLookupTable() {
        return lookupTable;
    }
}

public class Interpreter {
    public static Expr reduce(Expr expr) throws Throwable
    {
        return reduce(expr, new Env());
    }

    public static Expr reduce(Expr expr, Env env) throws Throwable
    {
        // Look for leftmost outermost redex
        Expr current = expr;
        while (current instanceof Application)
        {
            env.getSpineStack().push((Application)current);
            current = ((Application) current).getLeft();
        }

        if (current instanceof Lambda)
        {
            // nothing to reduce since not applied
            if (env.getSpineStack().empty())
            {
                return expr;
            }
            // reducing
            else
            {
                Lambda lambda = (Lambda)current;
                Expr converted = replace(lambda.getParameter().getValue(), lambda.getBody().clone(), env.getSpineStack().pop().getRight());
                return putBackInGraph(expr, converted, env);
            }
        }
        else if (current instanceof BuiltIn)
        {
            BuiltIn function = (BuiltIn) current;
            if (function.getName().equals("+"))
            {
                // not enough arguments, we stop there
                if (env.getSpineStack().size() < 2)
                {
                    return expr;
                }
                else
                {
                    Expr arg1 = env.getSpineStack().pop().getRight();
                    Expr arg2 = env.getSpineStack().pop().getRight();
                    if (arg1 instanceof Integer && arg2 instanceof Integer)
                    {
                        Expr reduced = new Integer(((Integer) arg1).getValue() + ((Integer) arg2).getValue());
                        return putBackInGraph(expr, reduced, env);
                    }
                    else
                    {
                        throw new WrongTypeException();
                    }
                }
            }
            else if (function.getName().equals("head") || function.getName().equals("tail"))
            {
                if (env.getSpineStack().size() < 1)
                {
                    return expr;
                }
                else
                {
                    Expr arg = env.getSpineStack().pop().getRight();
                    if (arg instanceof Cons)
                    {
                        return (function.getName().equals("head")) ? ((Cons) arg).getHead() : ((Cons) arg).getTail();
                    }
                    else if (arg instanceof Nil)
                    {
                        return arg;
                    }
                    else
                    {
                        throw new WrongTypeException();
                    }
                }
            }
            else
            {
                throw new UnknownBuiltInFunction();
            }
        }
        else
        {
            return expr;
        }
    }

    private static Expr putBackInGraph(Expr graph, Expr value, Env env)
    {
        if (env.getSpineStack().empty())
        {
            return value;
        }
        else
        {
            Application parent = env.getSpineStack().peek();
            parent.setLeft(value);
            return graph;
        }
    }

    public static Expr replace(String identifier, Expr expr, Expr value)
    {
        if (expr instanceof Identifier)
        {
            if (((Identifier) expr).getValue().equals(identifier))
            {
                return value;
            }
        }
        if (expr instanceof Application)
        {
            Application application = (Application)expr;
            application.setLeft(replace(identifier, application.getLeft(), value));
            application.setRight(replace(identifier, application.getRight(), value));
            return application;
        }
        else if (expr instanceof Lambda)
        {
            Lambda lambda = (Lambda) expr;
            if (!lambda.getParameter().getValue().equals(identifier))
            {
                return replace(identifier, lambda.getBody().clone(), value);
            }
        }
        return expr;
    }
}
