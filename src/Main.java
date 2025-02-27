
import java.util.ArrayList;
import Tokenizer.*;
public class Main {
    public static void printTokens(ArrayList<Token> tokens){
        for(Token token : tokens){
            System.out.println(token);
        }
    }

    public static void main(String[] args) {

        String example = "2x+3y+cos(sqrt(2angle))";
        var tokens = Tokenizer.tokenize(example);
        printTokens(tokens);

    }
}
