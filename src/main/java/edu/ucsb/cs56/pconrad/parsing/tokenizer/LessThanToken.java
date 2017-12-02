package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class LessThanToken extends Token {

    String value;
    public LessThanToken() {
        this.value = "<";
    }

    @Override
    public String toString() {
        return "LTToken" + "(" + value + ")";
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
