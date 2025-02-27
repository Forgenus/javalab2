
import java.util.ArrayList;
import Tokenizer.*;
public class Main {
    public static void printTokens(ArrayList<Token> tokens){
        for(Token token : tokens){
            System.out.println(token);
        }
    }

    public static void main(String[] args) {

        String input = "-2x+3y/(10sqrt(pi/2))";
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.tokenize(input);
        var tokens = tokenizer.getTokens();
        printTokens(tokens);

    }
}
