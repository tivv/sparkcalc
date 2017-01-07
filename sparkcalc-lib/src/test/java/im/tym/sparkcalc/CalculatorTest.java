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
    public void testPlusMinusMultiply() throws Exception {
        Assert.assertEquals(1+1-4*4.0, calculator.apply("1+1-4*4"));
    }

    @Test
    public void testMultiplyPlus() throws Exception {
        Assert.assertEquals(1+1-4*4.0, calculator.apply("-4*4 + 1 + 1"));
    }

    @Test
    public void testJustNumber() throws Exception {
        Assert.assertEquals(4.0, calculator.apply("+4e0"));
    }

    @Test
    public void testPlusMinus() throws Exception {
        Assert.assertEquals(-1.0, calculator.apply("1+-2"));
    }
}