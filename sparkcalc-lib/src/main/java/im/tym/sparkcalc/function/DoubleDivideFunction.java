package im.tym.sparkcalc.function;

import im.tym.sparkcalc.parse.FunctionToken;

/**
 * @author vit@tym.im
 */
public class DoubleDivideFunction extends SimpleOneCharFunction {
    protected DoubleDivideFunction() {
        super('/', 2);
    }

    @Override
    public Number calculate(FunctionToken token, Number left, Number right) {
        return left.doubleValue() / right.doubleValue();
    }
}
