package im.tym.sparkcalc.parse;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author vit@tym.im
 */
public class BaseParser implements Parser{
    private static final Pattern NUMBER_REGEXP = Pattern.compile("[+-]?[0-9]*(?:[.][0-9]*)?(?:[eE][+-]?[0-9]+)?");
    public static final BaseParser INSTANCE = new BaseParser();

    @Override
    public Spliterator<Token> apply(final String s) {
        return new Spliterators.AbstractSpliterator<Token>(s.length(), Spliterator.NONNULL + Spliterator.IMMUTABLE + Spliterator.ORDERED) {
            private int pos;
            private boolean nextNumber = true;
            private final Matcher numberMather = NUMBER_REGEXP.matcher(s);
            @Override
            public boolean tryAdvance(Consumer<? super Token> action) {
                while (pos < s.length() && Character.isWhitespace(s.charAt(pos))) {
                    pos++;
                }
                if (pos == s.length())
                    return false;
                if (nextNumber) {
                    if (!numberMather.find(pos) || numberMather.start() != pos) {
                        throw new IllegalArgumentException("Expect number at position " + pos + " in " + s);
                    }
                    action.accept(new NumberToken(Double.valueOf(numberMather.group())));
                    nextNumber = false;
                    pos = numberMather.end();
                } else {
                    action.accept(new OneCharFunctionToken(s.charAt(pos)));
                    nextNumber = true;
                    pos++;
                }
                return true;
            }
        };
    }
}
