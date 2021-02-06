package bignumbersexample;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class Arithmetics {
    private static final Logger LOGGER = LoggerFactory.getLogger(Arithmetics.class);

    /**
     * Creating a BigDecimal sets the scale and precision automatically. The number is always calculated with
     * the needed precision and then depending on the scale is rounded.
     */
    @Test
    public void givenRandomBigDecimals_whenCreatingBigDecimals_willDecideScaleAndPrecisionAutomatically() {
        BigDecimal first = new BigDecimal("12345.123");
        BigDecimal second = new BigDecimal("123.123456");

        assertEquals(3, first.scale());
        assertEquals(8, first.precision());

        assertEquals(6, second.scale());
        assertEquals(9, second.precision());
    }

    /**
     * Arithmetic operations with BigDecimal return new BigDecimal as it is immutable. The new object
     * has new precision and scale set appropriately without loosing information.
     */
    @Test
    public void givenRandomBigDecimals_whenArithmeticOperation_willCreateNewBigDecimalWithNewScaleAndPrecision() {
        BigDecimal first = new BigDecimal("12345.123");
        BigDecimal second = new BigDecimal("123.123456");
        BigDecimal sumResult = first.add(second);
        BigDecimal multiplyResult = first.multiply(second);

        assertEquals(6, sumResult.scale());
        assertEquals(11, sumResult.precision());
        assertEquals(9, multiplyResult.scale());
        assertEquals(16, multiplyResult.precision());
    }

    /**
     * Rounding needs a precision and mode to be input.
     */
    @Test
    public void givenBigDecimal_whenRounding_willReturnRounded() {
        BigDecimal first = new BigDecimal("12345.123");
        BigDecimal rounded = first.round(new MathContext(2, RoundingMode.CEILING));

        assertEquals("1.3E+4", rounded.toString());
    }

    /**
     * Dividing will return the number with the same scale as the divided BigDecimal. The BigIntegers are divided.
     * Then the scales are divided. After we have the accurate result, the scale of the divided BigDecimal is applied.
     * The precision is decided automatically. The precision is decided based on the needed for the integer part.
     * This is good when working with numbers, which will have a specific desired scale, like money.
     */
    @Test
    public void givenBigDecimal_whenDividing_willReturnDivided() {
        BigDecimal first = new BigDecimal("12345.123");
        BigDecimal second = new BigDecimal("123.157");
        BigDecimal divided = first.divide(second, RoundingMode.HALF_UP);

        assertEquals(new BigDecimal("100.239"), divided);
    }

    /**
     * If we use a Math Context, then we can set the precision instead of the scale. Again, the calculation is done
     * with the BigIntegers, calculating the correct result, and then rounded to our requirements. The scale is set
     * automatically.
     * This is good for scientific calculations.
     */
    @Test
    public void givenBigDecimal_whenDividingWithMathContext_willRetainSetPrecision() {
        BigDecimal first = new BigDecimal("12345.123");
        BigDecimal second = new BigDecimal("123.157");
        BigDecimal divided = first.divide(second, new MathContext(10, RoundingMode.HALF_UP));

        assertEquals(new BigDecimal("100.2389064"), divided);
    }

    /**
     * Changing the scale returns a new BigDecimal with updated scale and precision.
     */
    @Test
    public void givenBigDecimal_whenSetScale_willUpdatePrecisionAndSetScale() {
        BigDecimal number = new BigDecimal("11112345.123");
        BigDecimal result = number.setScale(-1, RoundingMode.HALF_UP);

        assertEquals(-1, result.scale());
        assertEquals("1.111235E+7", result.toString());
    }

    /**
     * Normal toString returns exponents in certain conditions. So does engineeringToString, however its exponent
     * is a multiple of three. toPlainString returns the number always without exponent.
     */
    @Test
    public void toStringing() {
        BigDecimal number = new BigDecimal("123456").setScale(-1, BigDecimal.ROUND_HALF_UP);

        assertEquals("1.2346E+5", number.toString());
        assertEquals("123460", number.toPlainString());
        assertEquals("123.46E+3", number.toEngineeringString());
    }
}
