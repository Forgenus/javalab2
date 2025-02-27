package Calculator;

public class Calculator {

    public static double calculate(String source){

        Tokenizer tokenizer = new Tokenizer();
        tokenizer.tokenize(source);
        var tokens = tokenizer.getTokens();
        var res = Converter.infixToRpn(tokens);
        return Evaluator.evaluate(res);
    }


}
