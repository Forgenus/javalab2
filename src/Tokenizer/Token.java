package Tokenizer;

public class Token {
    public enum Type {
        OPERATOR,
        FUNCTION,
        INT,
        FLOAT,
        VARIABLE,
        SEPARATOR
    }

    Token(Type type, String str) {
        this.type = type;
        this.value = str;
    }
    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
    private Type type;
    private String value;
}