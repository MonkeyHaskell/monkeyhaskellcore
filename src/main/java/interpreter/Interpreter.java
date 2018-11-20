package interpreter;

import node.*;
import node.literal.Literal;

import java.util.HashMap;
import java.util.Map;

public class Interpreter {

    private final Map<String, Node> lookupTable = new HashMap<>();

    public static void evaluate(Node node) throws NotARecognizedNodeTypeException {
        if (node instanceof Identifier) {

        } else if (node instanceof Application) {

        } else if (node instanceof BuiltInFunction) {

        } else if (node instanceof LambdaAbstraction) {

        } else if (node instanceof Identifier) {

        } else if (node instanceof Constructor) {

        } else if (node instanceof Literal) {

        } else {
            throw new NotARecognizedNodeTypeException();
        }

    }

    public void evaluateIdentifier(final Identifier identifier) {
        /* Remplacer l'identifier par son contenu, ou envoyer une exception */
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

    }

    public static void

    public static boolean isWHNF(final Node node) {
        return false;
    }
}
