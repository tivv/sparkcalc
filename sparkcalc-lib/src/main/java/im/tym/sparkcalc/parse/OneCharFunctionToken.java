package im.tym.sparkcalc.parse;

/**
 * @author vit@tym.im
 */
public class OneCharFunctionToken implements FunctionToken {
    private final char functionChar;

    public OneCharFunctionToken(char functionChar) {
        this.functionChar = functionChar;
    }

    public char getFunctionChar() {
        return functionChar;
    }

    @Override
    public String toString() {
        return "OneCharFunctionToken{" +
                "functionChar=" + functionChar +
                '}';
    }
}
