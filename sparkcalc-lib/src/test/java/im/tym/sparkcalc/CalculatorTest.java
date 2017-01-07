package im.tym.sparkcalc;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author vit@tym.im
 */
public class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    public void testApply() throws Exception {
        Assert.assertEquals(1+1-4*4, calculator.apply("1+1-4*4"));
    }
}