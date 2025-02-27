package Calculator;
import java.util.Objects;

 class Token {
    public enum Type {
        OPERATOR,
        FUNCTION,
        INT,
        FLOAT,
        VARIABLE,
        SEPARATOR,
        L_PARENTHESIS,
        R_PARENTHESIS
    }

    private Type type;
    private String value;

    Token(Type type, String str) {
        this.type = type;
        this.value = str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return type == token.type && Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }

    public Type getType() {
        return type;
    }


    public String getValue() {
        return value;
    }
}