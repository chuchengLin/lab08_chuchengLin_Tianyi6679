package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class EqualsToken extends Token {

    String value;
    public EqualsToken() {
        this.value = "==";
    }

    @Override
    public String toString() {
        return "EQToken" + "(" + value + ")";
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj)    {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        EqualsToken other = (EqualsToken) obj;
        return other.value.equals(this.value);
    }
}
