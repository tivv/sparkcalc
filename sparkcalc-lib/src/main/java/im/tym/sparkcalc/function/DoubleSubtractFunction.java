package im.tym.sparkcalc.function;

import im.tym.sparkcalc.parse.FunctionToken;

/**
 * @author vit@tym.im
 */
public class DoubleSubtractFunction extends SimpleOneCharFunction {
    protected DoubleSubtractFunction() {
        super('-', 1);
    }

    @Override
    public Number calculate(FunctionToken token, Number left, Number right) {
        return left.doubleValue() - right.doubleValue();
    }
}
