package interpreter;

import expr.Application;
import expr.Expr;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Environment {

    private final Stack<Application> spineStack;
    private final Map<String, Expr> lookupTable; /* currently not used */

    public Environment() {
        this.spineStack = new Stack<>();
        this.lookupTable = new HashMap<>();
    }

    public Environment(Stack<Application> spineStack, Map<String, Expr> lookupTable) {
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
