package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class GreaterThanToken extends Token {


    String value;
    public GreaterThanToken() {
        this.value = ">";
    }

    @Override
    public String toString() {
        return "GTToken" + "(" + value + ")";
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
