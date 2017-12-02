package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Default implementation of the <code>ASTFactory</code> interface.
 * This is used in the test suites to create ASTs in a manner which is
 * decoupled from the actual AST constructors.
 */

public class DefaultASTFactory implements ASTFactory {
    // begin constants
    public static final DefaultASTFactory DEFAULT = new DefaultASTFactory();
    // end constants

    public AST makeLiteral(final int value) {
        return new Literal(value);
    }

    public AST makePlusNode(final AST left, final AST right) {
        return new Binop(left, Plus.PLUS, right);
    }

    public AST makeMinusNode(final AST left, final AST right) {
        return new Binop(left, Minus.MINUS, right);
    }

    public AST makeTimesNode(final AST left, final AST right) {
        return new Binop(left, Times.TIMES, right);
    }

    public AST makeDivNode(final AST left, final AST right) {
        return new Binop(left, Div.DIV, right);
    }

    public AST makeUnaryMinusNode(final AST inner) {
        return new UnaryMinus(inner);
    }


    // This stub AST is a stub value that can be returned by
    // factory methods that are not implemented yet.  It has no purpose
    // in the final solution and can/should be removed.

    public static final AST stubAST = new AST() {
        public String toString() {
            return "AST(Stub)";
        }
    };

    // New AST nodes

    public AST makeEqualsNode(AST left, AST right) {
        return stubAST;
    }
    public AST makeNotEqualsNode(AST left, AST right) {
        return stubAST;
    }
    public AST makeLessThanNode(AST left, AST right) {
        return stubAST;
    }
    public AST makeLessThanOrEqualsNode(AST left, AST right) {
        return stubAST;
    }
    public AST makeGreaterThanNode(AST left, AST right) {
        return stubAST;
    }
    public AST makeGreaterThanOrEqualsNode(AST left, AST right) {
        return stubAST;
    }

    public AST makeExponentNode(AST left, AST right) {
        return stubAST;
    }


} // DefaultASTFactory

