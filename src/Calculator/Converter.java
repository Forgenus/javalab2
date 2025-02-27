package Calculator;

import java.util.*;

import static Calculator.Token.Type.*;


public class Converter {

    private static final HashMap<String, Integer> Precedence = new HashMap<>(Map.of(
            "&", 2,
            "+", 3,
            "-", 3,
            "*", 4,
            "/", 4,
            "^", 5
    ));

    private static int getPrecedence(Token op) {
        int val = Precedence.getOrDefault(op.getValue(), -1);
        if(val == -1)
            throw new RuntimeException("Unknown operation");
        return val;
    }

    public static Deque<Token> infixToRpn(ArrayList<Token> source) {
        Deque<Token> output = new ArrayDeque<>();
        Stack<Token> opStack = new Stack<>();
int i =0;
        for (var currentOp : source) {
            var type = currentOp.getType();
            i++;
            switch (type) {
                case INT:
                case FLOAT:
                case VARIABLE:
                    output.add(currentOp);
                    break;

                case FUNCTION:
                    opStack.push(currentOp);
                    break;

                case SEPARATOR: {
                    while(opStack.peek().getType()!=L_PARENTHESIS){
                        if(opStack.isEmpty())
                            throw new RuntimeException("Missing '(' or ','");
                        output.add(opStack.pop());
                    }
                    break;
                }

                case OPERATOR: {
                    while(!opStack.isEmpty() && opStack.peek().getType()==OPERATOR&&getPrecedence(opStack.peek())>=getPrecedence(currentOp)){
                        output.add(opStack.pop());
                    }
                    opStack.add(currentOp);
                    break;
                }

                case L_PARENTHESIS: {
                    opStack.push(currentOp);
                    break;
                }

                case R_PARENTHESIS: {
                    while (opStack.peek().getType()!=L_PARENTHESIS){
                        if(opStack.isEmpty())
                            throw new RuntimeException("Missing parenthesis");
                        output.add(opStack.pop());
                    }
                    opStack.pop();
                    if(!opStack.isEmpty() && opStack.peek().getType()==FUNCTION)
                        output.add(opStack.pop());
                   break;
                }

            }
        }
        while(!opStack.isEmpty()){
            if(Objects.equals(opStack.peek().getValue(), "("))
                throw new RuntimeException("There is a missing parenthesis in the expression");
            output.add(opStack.pop());
        }
        return output;
    }

}


