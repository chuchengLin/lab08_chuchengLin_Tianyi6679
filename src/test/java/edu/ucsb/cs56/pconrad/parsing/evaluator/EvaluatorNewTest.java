package edu.ucsb.cs56.pconrad.parsing.evaluator;

import edu.ucsb.cs56.pconrad.parsing.syntax.*;

import static edu.ucsb.cs56.pconrad.parsing.evaluator.EvaluatorTest.evaluateNoException;
import static edu.ucsb.cs56.pconrad.parsing.evaluator.EvaluatorTest.evaluate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import org.junit.Rule;
import org.junit.rules.ExpectedException;


/**
 * Like <code>EvaluatorTest</code>, but it adds in
 * tests for the new grammar.  This would normally be put
 * in <code>EvaluatorTest</code>, but here we use a separate
 * file to help prevent merge conflicts from occurring.
 * @see edu.ucsb.cs56.pconrad.parsing.evaluator.EvaluatorTest
 */
public class EvaluatorNewTest {
    // BEGIN INSTANCE VARIABLES
    private final ASTFactory af;
    // END INSTANCE VARIABLES

    public EvaluatorNewTest() {
        af = DefaultASTFactory.DEFAULT;
    }

    @Test
    public void testEqualsEquals() {
        assertEquals(1,
                     evaluateNoException(af.makeEqualsNode(af.makeLiteral(5),
                                                           af.makeLiteral(5))));
    }

    @Test
    public void testEqualsNotEquals() {
        assertEquals(0,
                     evaluateNoException(af.makeEqualsNode(af.makeLiteral(5),
                                                           af.makeLiteral(6))));
    }

    @Test
    public void testNotEqualsEquals() {
        assertEquals(0,
                     evaluateNoException(af.makeNotEqualsNode(af.makeLiteral(5),
                                                              af.makeLiteral(5))));
    }
    
    @Test
    public void testNotEqualsNotEquals() {
        assertEquals(1,
                     evaluateNoException(af.makeNotEqualsNode(af.makeLiteral(5),
                                                              af.makeLiteral(6))));
    }

    @Test
    public void test_GreaterThan_WithEqualValues() {
        assertEquals(0,
                     evaluateNoException(af.makeGreaterThanNode(af.makeLiteral(5),
																af.makeLiteral(5))));
    }

    @Test
    public void test_GreaterThanEquals_WithEqualValues() {
        assertEquals(1,
                     evaluateNoException(af.makeGreaterThanOrEqualsNode(af.makeLiteral(5),
																	  af.makeLiteral(5))));
    }

    @Test
    public void test_LessThan_WithEqualValues() {
        assertEquals(0,
                     evaluateNoException(af.makeLessThanNode(af.makeLiteral(5),
																af.makeLiteral(5))));
    }

    @Test
    public void test_LessThanEquals_WithEqualValues() {
        assertEquals(1,
                     evaluateNoException(af.makeLessThanOrEqualsNode(af.makeLiteral(5),
																	  af.makeLiteral(5))));
    }


	@Test
    public void test_GreaterThan_FirstLessThanSecond() {
        assertEquals(0,
                     evaluateNoException(af.makeGreaterThanNode(af.makeLiteral(5),
																af.makeLiteral(7))));
    }

    @Test
    public void test_GreaterThanEquals_FirstLessThanSecond() {
        assertEquals(0,
                     evaluateNoException(af.makeGreaterThanOrEqualsNode(af.makeLiteral(5),
																	  af.makeLiteral(7))));
    }

    @Test
    public void test_LessThan_FirstLessThanSecond() {
        assertEquals(1,
                     evaluateNoException(af.makeLessThanNode(af.makeLiteral(5),
															 af.makeLiteral(7))));
    }

    @Test
    public void test_LessThanEquals_FirstLessThanSecond() {
        assertEquals(1,
                     evaluateNoException(af.makeLessThanOrEqualsNode(af.makeLiteral(5),
																   af.makeLiteral(7))));
    }

	@Test
    public void test_GreaterThan_SecondLessThanFirst() {
        assertEquals(1,
                     evaluateNoException(af.makeGreaterThanNode(af.makeLiteral(9),
																af.makeLiteral(7))));
    }

    @Test
    public void test_GreaterThanEquals_SecondLessThanFirst() {
        assertEquals(1,
                     evaluateNoException(af.makeGreaterThanOrEqualsNode(af.makeLiteral(9),
																	  af.makeLiteral(7))));
    }

    @Test
    public void test_LessThan_SecondLessThanFirst() {
        assertEquals(0,
                     evaluateNoException(af.makeLessThanNode(af.makeLiteral(9),
															 af.makeLiteral(7))));
    }

    @Test
    public void test_LessThanEquals_SecondLessThanFirst() {
        assertEquals(0,
                     evaluateNoException(af.makeLessThanOrEqualsNode(af.makeLiteral(9),
																   af.makeLiteral(7))));
    }

	
    @Test
    public void testTwoToTheThird() {
        assertEquals(8,
                     evaluateNoException(af.makeExponentNode(af.makeLiteral(2),
                                                              af.makeLiteral(3))));
    }

	@Test
    public void testTwoToTheZero() {
        assertEquals(1,
                     evaluateNoException(af.makeExponentNode(af.makeLiteral(2),
                                                              af.makeLiteral(0))));
    }

	@Test
    public void testTwoToTheNegativeOne() {
        assertEquals(0, /* after integer division */
                     evaluateNoException(af.makeExponentNode(af.makeLiteral(2),
                                                              af.makeLiteral(-1))));
    }

	@Test
    public void testNeg1ToTheZero() throws EvaluatorException {
        assertEquals(1,evaluate(af.makeExponentNode(af.makeLiteral(-1),
													 af.makeLiteral(0))));
	}

	
	@Test
    public void testNeg1ToTheNegative1() throws EvaluatorException {
        assertEquals(-1,evaluate(af.makeExponentNode(af.makeLiteral(-1),
													 af.makeLiteral(-1))));
	}

	
	@Test
    public void testNeg1Squared() throws EvaluatorException {
        assertEquals(1,evaluate(af.makeExponentNode(af.makeLiteral(-1),
													 af.makeLiteral(2))));
	}


    @Test
    public void testNeg1ToTheThird() throws EvaluatorException {
        assertEquals(-1,evaluate(af.makeExponentNode(af.makeLiteral(-1),
													 af.makeLiteral(3))));
	}



	
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testDivDirectZero() throws EvaluatorException {
        thrown.expect(EvaluatorException.class);
        thrown.expectMessage("Division by zero");		
        evaluate(af.makeDivNode(af.makeLiteral(14),
                                af.makeLiteral(0)));
    }

    @Test
    public void testZeroToTheZero() throws EvaluatorException {
        thrown.expect(EvaluatorException.class);
        thrown.expectMessage("0**0 is undefined");		
        evaluate(af.makeExponentNode(af.makeLiteral(0),
									 af.makeLiteral(0)));
    }


	

} // EvaluatorNewTest
