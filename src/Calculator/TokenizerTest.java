package Calculator;

import org.junit.jupiter.api.Test;

import static Calculator.Token.Type.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TokenizerTest {



    @Test
    public void testTokenizeWithComplexExpression() {

       String input = "-2x+3y/(10sqrt(pi/2))";
        List<Token> expected = Arrays.asList(
                new Token(OPERATOR, "&"),
                new Token(INT, "2"),
                new Token(OPERATOR, "*"),
                new Token(VARIABLE, "x"),
                new Token(OPERATOR, "+"),
                new Token(INT, "3"),
                new Token(OPERATOR, "*"),
                new Token(VARIABLE, "y"),
                new Token(OPERATOR, "/"),
                new Token(L_PARENTHESIS, "("),
                new Token(INT, "10"),
                new Token(OPERATOR, "*"),
                new Token(FUNCTION, "sqrt"),
                new Token(L_PARENTHESIS, "("),
                new Token(VARIABLE, "pi"),
                new Token(OPERATOR, "/"),
                new Token(INT, "2"),
                new Token(R_PARENTHESIS, ")"),
                new Token(R_PARENTHESIS, ")")
        );
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.tokenize(input);
        ArrayList<Token> actual = tokenizer.getTokens();
        assertEquals(expected, actual);
    }
}