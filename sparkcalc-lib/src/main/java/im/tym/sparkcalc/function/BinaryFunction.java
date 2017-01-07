package im.tym.sparkcalc.function;

import im.tym.sparkcalc.parse.FunctionToken;

/**
 * @author vit@tym.im
 */
public interface BinaryFunction {
    public static final int DONT_PROCESS = Integer.MIN_VALUE;

    /**
     *
     * @param functionToken
     * @return priority for given token or {@link BinaryFunction#DONT_PROCESS} if this function is not supported.
     * Functions with higher priority will be run first.
     */
    int accept(FunctionToken functionToken);
    Number calculate(FunctionToken token, Number left, Number right);
}
