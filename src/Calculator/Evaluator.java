package Calculator;

import java.util.Deque;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import static Calculator.Token.Type.*;

public class Evaluator {


    public static double evaluate(Deque<Token> que){
        HashMap<String,Double> variables = new HashMap<>();
        Stack<Double> stack = new Stack<>();
        while(!que.isEmpty()){

            Token currToken = que.pop();
            switch(currToken.getType()){
                case FLOAT:
                    stack.add(Double.parseDouble(currToken.getValue()));
                    break;
                case INT:
                    stack.add((double)Integer.parseInt(currToken.getValue()));
                    break;
                case VARIABLE:{
                    String varName = currToken.getValue();
                    double val;
                    if(variables.containsKey(varName))
                        val = variables.get(varName);
                    else{
                        System.out.println("Enter value for "+ varName );
                        Scanner console = new Scanner(System.in);
                        val = console.nextDouble();
                        variables.put(varName,val);
                    }
                    stack.add(val);
                    break;
                }
                case OPERATOR:{
                    switch(currToken.getValue()){
                        case "^":{
                            double right = stack.pop();
                            double left = stack.pop();
                            stack.add(Math.pow(left,right));
                            break;
                        }
                        case "&":{
                            stack.add(-1*stack.pop());
                            break;
                        }
                        case "+":{
                            double right = stack.pop();
                            double left = stack.pop();
                            stack.add(left+right);
                            break;
                        }
                        case "-":{
                            double right = stack.pop();
                            double left = stack.pop();
                            stack.add(left-right);
                            break;
                        }
                        case "*":{
                            double right = stack.pop();
                            double left = stack.pop();
                            stack.add(left*right);
                            break;
                        }
                        case "/":{
                            double right = stack.pop();
                            double left = stack.pop();
                            stack.add(left/right);
                            break;
                        }

                    }
                }
                case FUNCTION:{
                    switch(currToken.getValue()){
                        case "sqrt":{
                            double left = stack.pop();
                            stack.add(Math.sqrt(left));
                            break;
                        }
                        case "min":{
                            double right = stack.pop();
                            double left = stack.pop();
                            stack.add(Math.min(left,right));
                            break;
                        }
                        case "max":{
                            double right = stack.pop();
                            double left = stack.pop();
                            stack.add(Math.max(left,right));
                            break;
                        }
                        case "cos":{
                            double left = stack.pop();
                            stack.add(Math.cos(left));
                            break;
                        }
                        case "sin":{
                            double left = stack.pop();
                            stack.add(Math.sin(left));
                            break;
                        }
                        case "tan":{
                            double left = stack.pop();
                            stack.add(Math.tan(left));
                            break;
                        }
                        case "ctg":{
                            double left = stack.pop();
                            stack.add(1/Math.tan(left));
                            break;
                        }
                        case "log":{
                            double right = stack.pop();
                            double left = stack.pop();
                            stack.add(Math.log(left)/Math.log(right));
                            break;
                        }
                        case "ln":{
                            double left = stack.pop();
                            stack.add(Math.log(left));
                            break;
                        }
                        case "lg":{
                            double left = stack.pop();
                            stack.add(Math.log10(left));
                            break;
                        }
                    }
                }
            }




            }
        return stack.pop();
        }

    }

