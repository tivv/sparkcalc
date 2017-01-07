package im.tym.sparkcalc.function;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author vit@tym.im
 */
public final class Functions {
    private Functions() {}

    public static final List<BinaryFunction> DOUBLE_FUNCTIONS = Collections.unmodifiableList(Arrays.asList(
            new DoubleAddFunction(),
            new DoubleSubtractFunction(),
            new DoubleMultiplyFunction(),
            new DoubleDivideFunction()
    ));
}
