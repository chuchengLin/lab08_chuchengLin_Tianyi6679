package edu.ucsb.cs56.pconrad.parsing.evaluator;

import edu.ucsb.cs56.pconrad.parsing.syntax.*;

public class Evaluator {

    public static int evaluate(final int left,
                               final Operator operator,
                               final int right)
    throws EvaluatorException {
        if (operator instanceof Plus) {
            return left + right;
        } else if (operator instanceof Minus) {
            return left - right;
        } else if (operator instanceof Times) {
            return left * right;
        } else if (operator instanceof Div) {
            if (right == 0) {
                throw new EvaluatorException("Division by zero");
            } else {
                return left / right;
            }
        } else if (operator instanceof Equals){
	    if(left == right) return 1;
	    else {return 0;}
	} else if (operator instanceof NotEquals){
	    if(left == right) return 0;
	    else {return 1;}
	} else if (operator instanceof GreaterThan){
	    if(left > right) return 1;
	    else {return 0;}
	}else if (operator instanceof LessThan){
	    if(left < right) return 1;
	    else {return 0;}
	}else if (operator instanceof GreaterThanOrEquals){
	    if(left >= right) return 1;
	    else {return 0;}
	}else if (operator instanceof LessThanOrEquals){
	    if(left <= right) return 1;
	    else {return 0;}
	}else if (operator instanceof Exponent){
	    if(right>0){
	    		int ans = 1;
	    		for(int i=0; i<right; i++){
		 		ans = ans * left;
			}
			return ans;
	    }
	    else if(right == 0){
		if(left == 0) { throw new EvaluatorException("0**0 is undefined");}
		else return 1;
	    }
	    else{
		int opposite = right * (-1);
		int ans = 1;
	    	for(int i=0; i<opposite; i++){
		 	ans = ans * left;
			}
			return 1/ans;
	    }
	}
	else {
            throw new EvaluatorException("Unknown Operator Type");
        }
    } // evaluate

    public static int evaluate(final AST expression)
    throws EvaluatorException {
        if (expression instanceof Literal) {
            return ((Literal)expression).getValue();
        } else if (expression instanceof Binop) {
            final Binop binop = (Binop)expression;
            return evaluate(evaluate(binop.getLeft()),
                            binop.getOperator(),
                            evaluate(binop.getRight()));
        } else if (expression instanceof UnaryMinus) {
            return -evaluate(((UnaryMinus)expression).getNested());
        } else {
            throw new EvaluatorException("Unknown Expression Type");
        }
    } // evaluate
}
