package im.tym.sparkcalc.function;

import im.tym.sparkcalc.parse.FunctionToken;
import im.tym.sparkcalc.parse.OneCharFunctionToken;

/**
 * @author vit@tym.im
 */
public abstract class SimpleOneCharFunction implements BinaryFunction{
    private final char functionChar;
    private final int priority;

    protected SimpleOneCharFunction(char functionChar, int priority) {
        this.functionChar = functionChar;
        this.priority = priority;
    }

    @Override
    public int accept(FunctionToken functionToken) {
        if (!(functionToken instanceof OneCharFunctionToken)) {
            return DONT_PROCESS;
        }
        return functionChar == ((OneCharFunctionToken) functionToken).getFunctionChar() ? priority : DONT_PROCESS;
    }
}
