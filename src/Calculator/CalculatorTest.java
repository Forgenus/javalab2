package Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;


public class CalculatorTest {
    @Test
    public void testCalculator() {

        String input = "-2x+10y*sqrt(10/2*cos(1))";
        String simulatedInput = "2,5\n3,1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        double expected = 45.95245410867284;
        double actual = Calculator.calculate(input);
        double ratio = actual/expected;
        boolean equal = Math.abs(1-ratio) < 0.001;
        Assertions.assertTrue(equal);
    }
}
