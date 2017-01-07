package im.tym.sparkcalc.parse;

import java.util.Spliterator;
import java.util.function.Function;

/**
 * @author vit@tym.im
 */
public interface Parser extends Function<String, Spliterator<Token>>{
}
