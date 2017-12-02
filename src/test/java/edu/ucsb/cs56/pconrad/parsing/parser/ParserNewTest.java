package edu.ucsb.cs56.pconrad.parsing.parser;

import edu.ucsb.cs56.pconrad.parsing.tokenizer.*;
import edu.ucsb.cs56.pconrad.parsing.syntax.*;

import static edu.ucsb.cs56.pconrad.parsing.parser.ParserTest.parseNoException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;


/**
 * Like <code>ParserTest</code>, but it adds in
 * tests for the new grammar.  This would normally be put
 * in <code>ParserTest</code>, but here we use a separate
 * file to help prevent merge conflicts from occurring.
 * @see edu.ucsb.cs56.pconrad.parsing.parser.ParserTest
 */

public class ParserNewTest {
    // begin instance variables
    private final ASTFactory af;
    // end instance variables

    public ParserNewTest() {
        af = DefaultASTFactory.DEFAULT;
    }

    @Test
    public void testEqualsLiterals() {
        assertEquals(af.makeEqualsNode(af.makeLiteral(1),
                                       af.makeLiteral(2)),
                     parseNoException("1 == 2"));
    }

    @Test
    public void testLtLiterals() {
        assertEquals(af.makeLessThanNode(af.makeLiteral(1),
                                       af.makeLiteral(2)),
                     parseNoException("1<2"));
    }

    @Test
    public void testLeLiterals() {
        assertEquals(af.makeLessThanOrEqualsNode(af.makeLiteral(1),
                                       af.makeLiteral(2)),
                     parseNoException("1<=2"));
    }

    @Test
    public void testGtLiterals() {
        assertEquals(af.makeGreaterThanNode(af.makeLiteral(1),
                                       af.makeLiteral(2)),
                     parseNoException("1>2"));
    }

    @Test
    public void testGeLiterals() {
        assertEquals(af.makeGreaterThanOrEqualsNode(af.makeLiteral(1),
                                       af.makeLiteral(2)),
                     parseNoException("1>=2"));
    }
	
    @Test
    public void testNotEqualsLiterals() {
        assertEquals(af.makeNotEqualsNode(af.makeLiteral(1),
                                          af.makeLiteral(2)),
                     parseNoException("1 != 2"));
    }

    @Test
    public void testExponentLiterals() {
        assertEquals(af.makeExponentNode(af.makeLiteral(2),
                                         af.makeLiteral(3)),
                     parseNoException("2**3"));
    }

    @Test
    public void testExponentAssociativity() {
        assertEquals(af.makeExponentNode(af.makeLiteral(2),
                                         af.makeExponentNode(af.makeLiteral(3),
                                                 af.makeLiteral(2))),
                     parseNoException("2**3**2"));
    }

    @Test
    public void testExponentAssociativityWithParensRight() {
        assertEquals(af.makeExponentNode(af.makeLiteral(2),
                                         af.makeExponentNode(af.makeLiteral(3),
                                                 af.makeLiteral(2))),
                     parseNoException("2**(3**2)"));
    }

    @Test
    public void testExponentAssociativityWithParensLeft() {
        assertEquals(af.makeExponentNode(af.makeExponentNode(af.makeLiteral(2),
                                         af.makeLiteral(3)),
                                         af.makeLiteral(2)),
                     parseNoException("(2**3)**2"));
    }

} // ParserNewTest
