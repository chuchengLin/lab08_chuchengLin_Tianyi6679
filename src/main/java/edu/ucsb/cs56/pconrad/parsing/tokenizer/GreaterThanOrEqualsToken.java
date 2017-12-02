package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class GreaterThanOrEqualsToken extends Token {


    String value;
    public GreaterThanOrEqualsToken() {
        this.value = ">=";
    }

    @Override
    public String toString() {
        return "GEToken" + "(" + value + ")";
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
