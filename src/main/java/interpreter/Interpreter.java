package interpreter;

import node.*;
import node.literal.Literal;

import java.util.HashMap;
import java.util.Map;

public class Interpreter {

    private final Map<String, Node> lookupTable = new HashMap<>();

    public static void evaluate(Node node) throws NotARecognizedNodeTypeException {
        if (node instanceof Identifier) {
            evaluateIdentifier((Identifier)node);
        } else if (node instanceof Application) {
            evaluateApplication((Application)node);
        } else if (node instanceof BuiltInFunction) {
            evaluateBuiltInFunction((BuiltInFunction)node);
        } else if (node instanceof LambdaAbstraction) {
            evaluateLambdaAbstraction((LambdaAbstraction)node);
        } else if (node instanceof Constructor) {
            evaluateConstructor((Constructor)node);
        } else if (node instanceof Literal) {
            evaluateLiteral((Literal)node);
        } else {
            throw new NotARecognizedNodeTypeException();
        }
    }

    public static void evaluateIdentifier(final Identifier identifier) {
        /* Remplacer l'identifier par son contenu, ou envoyer une exception si pas trouvé */
    }

    public static void evaluateApplication(final Application application) {
        /* LambdaAbstraction à gauche
            Récupère le paramètre
            Parcours du body
            Remplacement des Identifier portant le même nom que le paramètre par l'arbre droit
            Il faudrait remplacer l'application par le body obtenu
         */

        /* Identifier à gauche
            On evaluate identifier
         */

        /* Application à gauche :

         */

        /* BuiltIn à gauche :
            On appelle la fonction
         */
    }

    public static void evaluateBuiltInFunction(final BuiltInFunction builtInFunction) {
        /* Imo :
            Se remplace soit par une lambdaAbstraction si pas assez d'argument,
            Soit par un literal ?
         */
    }

    public static void evaluateLambdaAbstraction(final LambdaAbstraction lambdaAbstraction) {

    }

    public static void evaluateConstructor(final Constructor constructor) {

    }

    public static void evaluateLiteral(final Literal literal) {

    }

    public static boolean isWHNF(final Node node) {
        return false;
    }
}
