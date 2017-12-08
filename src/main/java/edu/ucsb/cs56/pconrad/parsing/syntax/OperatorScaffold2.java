package edu.ucsb.cs56.pconrad.parsing.syntax;

public abstract class OperatorScaffold2 implements Operator {
    // begin instance variables
    protected final String repr;
    // end instance variables

    public OperatorScaffold2(String repr) {
        this.repr = repr;
    }

    public String getRepr() {
        return repr;
    }

    public String toString() {
        return repr;
    }

    public boolean equals(Object other) {
        return (other instanceof OperatorScaffold2 &&
                ((OperatorScaffold2)other).getRepr() == repr);
    }

    public int hashCode() {
        return repr.hashCode();
    }
}