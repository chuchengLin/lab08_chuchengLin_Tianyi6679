package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class NotEqualsToken extends Token {


    String value;
    public NotEqualsToken() {
        this.value = "!=";
    }

    @Override
    public String toString() {
        return "NEToken" + "(" + value + ")";
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
