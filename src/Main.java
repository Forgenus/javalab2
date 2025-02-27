
import java.util.ArrayList;
import java.util.Deque;

import Calculator.*;
public class Main {
    public static void printTokens(ArrayList<Token> tokens){
        for(Token token : tokens){
            System.out.println(token);
        }
    }
    public static void printTokens(Deque<Token> tokens){
       while(!tokens.isEmpty()){
           System.out.println(tokens.pop());
       }
    }

    public static void main(String[] args) {

        String input = "2x+10y*sqrt(10/2*cos(1))";
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.tokenize(input);
        var tokens = tokenizer.getTokens();
        var res = Converter.infixToRpn(tokens);
        double val  = Evaluator.evaluate(res);
        System.out.println(val);

    }
}
