package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class LessThanOrEqualsToken extends Token {


    String value;
    public LessThanOrEqualsToken() {
        this.value = "<=";
    }

    @Override
    public String toString() {
        return "LEToken" + "(" + value + ")";
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
