package Calculator;

import org.junit.jupiter.api.Test;
import static Calculator.Token.Type.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class ConverterTest {

    @Test
    public void testConverter() {

        String input = "2x+10y*sqrt(10/2*cos(1))";
        List<Token> expected = Arrays.asList(
                new Token(INT, "2"),
                new Token(VARIABLE, "x"),
                new Token(OPERATOR, "*"),
                new Token(INT, "10"),
                new Token(VARIABLE, "y"),
                new Token(OPERATOR, "*"),
                new Token(INT, "10"),
                new Token(INT, "2"),
                new Token(OPERATOR, "/"),
                new Token(INT, "1"),
                new Token(FUNCTION, "cos"),
                new Token(OPERATOR, "*"),
                new Token(FUNCTION, "sqrt"),
                new Token(OPERATOR, "*"),
                new Token(OPERATOR, "+")
        );
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.tokenize(input);
        Deque<Token> actualDeque = Converter.infixToRpn(tokenizer.getTokens());
        List<Token> actual = new ArrayList<>(actualDeque);
        assertEquals(expected, actual);
    }
}