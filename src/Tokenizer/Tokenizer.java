package Tokenizer;

import java.util.ArrayList;


public class Tokenizer {


    public static ArrayList<Token> tokenize(String str) {

        ArrayList<Token> result = new ArrayList<>();
        StringBuilder numberBuffer = new StringBuilder();
        StringBuilder letterBuffer = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (Character.isDigit(ch) || ch == '.') { // if character is a digit
                numberBuffer.append(ch);

                    if (!letterBuffer.isEmpty()) {
                    str = str.substring(0,i) + '_' + str.substring(i);
                    throw new RuntimeException("letter that is followed by a number is invalid\n"+str);
                }
            } else if (Character.isLetter(ch)) { // if character is a letter
                letterBuffer.append(ch);

                if (!numberBuffer.isEmpty()) {

                    Token.Type type = numberBuffer.indexOf(".") == -1 ? Token.Type.INT : Token.Type.FLOAT;
                    Token token = new Token(type, numberBuffer.toString());
                    result.add(token);
                    // insert * operator
                    result.add(new Token(Token.Type.OPERATOR,"*"));
                    numberBuffer.setLength(0);
                }
            } else if(ch == '('){
                Token token = new Token(Token.Type.FUNCTION,letterBuffer.toString());
                result.add(token);
                letterBuffer.setLength(0);
            } else if(ch==')'){
                if(!numberBuffer.isEmpty() && !letterBuffer.isEmpty()){
                    str = str.substring(0,i) + '_' + str.substring(i);
                    throw new RuntimeException("both number and letter buffers hold values when encountering ')'\n"+str);
                }

                if(!numberBuffer.isEmpty()){
                    Token.Type type = numberBuffer.indexOf(".") == -1 ? Token.Type.INT : Token.Type.FLOAT;
                    Token token = new Token(type, numberBuffer.toString());
                    result.add(token);
                    numberBuffer.setLength(0);

                } else if(!letterBuffer.isEmpty()){
                    Token token = new Token(Token.Type.VARIABLE,letterBuffer.toString());
                    result.add(token);
                    letterBuffer.setLength(0);
                }

            } else if(ch == ',') {
                if (numberBuffer.isEmpty() && letterBuffer.isEmpty()) {
                    str = str.substring(0, i) + '_' + str.substring(i);
                    throw new RuntimeException("both number and letter buffers are empty when encountering ,'\n" + str);
                }
                if (!numberBuffer.isEmpty() && !letterBuffer.isEmpty()) {
                    str = str.substring(0, i) + '_' + str.substring(i);
                    throw new RuntimeException("both number and letter buffers hold values when encountering ,'\n" + str);
                }

                if (!letterBuffer.isEmpty()) {
                    Token token = new Token(Token.Type.VARIABLE, letterBuffer.toString());
                    result.add(token);
                    letterBuffer.setLength(0);
                } else {
                    Token.Type type = numberBuffer.indexOf(".") == -1 ? Token.Type.INT : Token.Type.FLOAT;
                    Token token = new Token(type, numberBuffer.toString());
                    result.add(token);
                    numberBuffer.setLength(0);
                }
                Token token = new Token(Token.Type.SEPARATOR, ",");
            }else{
                switch(ch){

                    case '+':
                    case'-':
                    case '*':
                    case '/':
                    case '^':
                        if (!numberBuffer.isEmpty() && !letterBuffer.isEmpty()) {
                            str = str.substring(0, i) + '_' + str.substring(i);
                            throw new RuntimeException("both number and letter buffers hold values when encountering operator\n" + str);
                        }
                        if(!numberBuffer.isEmpty()){
                            Token.Type type = numberBuffer.indexOf(".") == -1 ? Token.Type.INT : Token.Type.FLOAT;
                            Token token = new Token(type, numberBuffer.toString());
                            result.add(token);
                            numberBuffer.setLength(0);
                        } else{
                            Token token = new Token(Token.Type.VARIABLE, letterBuffer.toString());
                            result.add(token);
                            letterBuffer.setLength(0);
                        }
                        Token token = new Token(Token.Type.OPERATOR,Character.toString(ch));
                        result.add(token);

                        break;
                    default:
                        str = str.substring(0, i) + '_' + str.substring(i);
                        throw new RuntimeException("Unknown symbol\n" + str);
                }
            }



        }

        if(!numberBuffer.isEmpty() && !letterBuffer.isEmpty()){
            throw new RuntimeException("both number and letter buffers hold values at the end of processing'\n");
        }
        if(!numberBuffer.isEmpty()){
            Token.Type type = numberBuffer.indexOf(".") == -1 ? Token.Type.INT : Token.Type.FLOAT;
            Token token = new Token(type,numberBuffer.toString());
            result.add(token);
            numberBuffer.setLength(0);
        } else if(!letterBuffer.isEmpty()){
            Token token = new Token(Token.Type.VARIABLE,letterBuffer.toString());
            result.add(token);
            letterBuffer.setLength(0);
        }

        return result;
    }


}
