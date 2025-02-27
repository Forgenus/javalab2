package Tokenizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Tokenizer {

    static String validOperators = "+-*/^";
    static Set<String> validFunctions = new HashSet<>();

    static {
        Collections.addAll(validFunctions, "sqrt", "sin", "cos", "tan", "log", "ln", "lg", "sin", "atan");
    }

    StringBuilder numberBuffer = new StringBuilder();
    StringBuilder letterBuffer = new StringBuilder();
    ArrayList<Token> tokens;

    private boolean areBuffersFilled() {
        return !numberBuffer.isEmpty() && !letterBuffer.isEmpty();
    }

    private boolean areBuffersEmpty() {
        return numberBuffer.isEmpty() && letterBuffer.isEmpty();
    }

    private void dumpNumberBuffer() {
        dumpNumberBuffer(false);
    }

    private void dumpNumberBuffer(boolean addMultiplication) {


        Token.Type type = numberBuffer.indexOf(".") == -1 ? Token.Type.INT : Token.Type.FLOAT;
        Token token = new Token(type, numberBuffer.toString());
        tokens.add(token);
        numberBuffer.setLength(0);

        if (addMultiplication)
            tokens.add(new Token(Token.Type.OPERATOR, "*"));

    }

    private void dumpLetterBuffer() {
        dumpLetterBuffer(false);
    }

    private void dumpLetterBuffer(boolean function) {
        Token.Type type = function ? Token.Type.FUNCTION : Token.Type.VARIABLE;
        Token token = new Token(type, letterBuffer.toString());
        tokens.add(token);
        letterBuffer.setLength(0);
    }

    private void throwException(String message, String str, int charPos) {
        str = str.substring(0, charPos) + '_' + str.substring(charPos);
        throw new RuntimeException(message + '\n' + str);
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void tokenize(String str) {


        tokens = new ArrayList<>();


        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (Character.isDigit(ch) || ch == '.') { // if character is a digit
                numberBuffer.append(ch);

                if (!letterBuffer.isEmpty()) {
                    throwException("letter that is followed by a number is invalid", str, i);
                }
            } else if (Character.isLetter(ch)) { // if character is a letter
                letterBuffer.append(ch);

                if (!numberBuffer.isEmpty())
                    dumpNumberBuffer(true);

            } else if (ch == '(') {
                boolean isFunc = (letterBuffer.isEmpty() || validFunctions.contains(letterBuffer.toString()));
                if(!letterBuffer.isEmpty()) {
                    dumpLetterBuffer(isFunc);
                }
                tokens.add(new Token(Token.Type.L_PARENTHESIS,"("));
            } else if (ch == ')') {

                if (areBuffersFilled())
                    throwException("both number and letter buffers hold values when encountering ')'", str, i);

                if (!numberBuffer.isEmpty())
                    dumpNumberBuffer();
                else if (!letterBuffer.isEmpty())
                    dumpLetterBuffer();

                tokens.add(new Token(Token.Type.R_PARENTHESIS,")"));

            } else if (ch == ',') {
                if (areBuffersEmpty())
                    throwException("both number and letter buffers are empty when encountering ,", str, i);

                if (areBuffersFilled())
                    throwException("both number and letter buffers hold values when encountering ,", str, i);


                if (!letterBuffer.isEmpty())
                    dumpLetterBuffer();
                else
                    dumpNumberBuffer();

                tokens.add(new Token(Token.Type.SEPARATOR, ","));

            } else if (validOperators.contains(Character.toString(ch))) {

                if (areBuffersFilled() && ch != '-')
                    throwException("both number and letter buffers hold values when encountering binary operator", str, i);

                if (!numberBuffer.isEmpty()) {
                    dumpNumberBuffer();
                } else if (!letterBuffer.isEmpty()) {
                    dumpLetterBuffer();
                }
                //check if '-' is unary or binary
                if (ch == '-' && (tokens.isEmpty() || tokens.getLast().getType() == Token.Type.L_PARENTHESIS))
                    tokens.add(new Token(Token.Type.OPERATOR, "&"));
                else
                    tokens.add(new Token(Token.Type.OPERATOR, Character.toString(ch)));


            } else
                throwException("Unknown symbol", str, i);


            if (areBuffersFilled())
                throwException("both number and letter buffers hold values at the end of processing", str, i);


        }
        if (!numberBuffer.isEmpty())
            dumpNumberBuffer();
        else if (!letterBuffer.isEmpty())
            dumpLetterBuffer();
    }

}