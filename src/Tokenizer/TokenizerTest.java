package Tokenizer;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TokenizerTest {



    @Test
    public void testTokenizeWithComplexExpression() {
        Tokenizer tokenizer = new Tokenizer();
       String input = "-2x+3y/(10sqrt(pi/2))";
        List<Token> expected = Arrays.asList(
                new Token(Token.Type.OPERATOR, "&"),
                new Token(Token.Type.INT, "2"),
                new Token(Token.Type.OPERATOR, "*"),
                new Token(Token.Type.VARIABLE, "x"),
                new Token(Token.Type.OPERATOR, "+"),
                new Token(Token.Type.INT, "3"),
                new Token(Token.Type.OPERATOR, "*"),
                new Token(Token.Type.VARIABLE, "y"),
                new Token(Token.Type.OPERATOR, "/"),
                new Token(Token.Type.L_PARENTHESIS, "("),
                new Token(Token.Type.INT, "10"),
                new Token(Token.Type.OPERATOR, "*"),
                new Token(Token.Type.FUNCTION, "sqrt"),
                new Token(Token.Type.L_PARENTHESIS, "("),
                new Token(Token.Type.VARIABLE, "pi"),
                new Token(Token.Type.OPERATOR, "/"),
                new Token(Token.Type.INT, "2"),
                new Token(Token.Type.R_PARENTHESIS, ")"),
                new Token(Token.Type.R_PARENTHESIS, ")")
        );
        tokenizer.tokenize(input);
        ArrayList<Token> actual = tokenizer.getTokens();
        assertEquals(expected, actual);
    }
}