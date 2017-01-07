package im.tym.sparkcalc.parse;

/**
 * @author vit@tym.im
 */
public class NumberToken implements Token {
    private final Number number;

    public NumberToken(Number number) {
        this.number = number;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "NumberToken{" +
                "number=" + number +
                '}';
    }
}
