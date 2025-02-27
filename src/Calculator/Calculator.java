package Calculator;

import java.util.ArrayList;
import java.util.Deque;

public class Calculator {
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
    public static double calculate(String source){

        Tokenizer tokenizer = new Tokenizer();
        tokenizer.tokenize(source);
        var tokens = tokenizer.getTokens();
        var res = Converter.infixToRpn(tokens);
        printTokens(res);
        return Evaluator.evaluate(res);
    }


}
