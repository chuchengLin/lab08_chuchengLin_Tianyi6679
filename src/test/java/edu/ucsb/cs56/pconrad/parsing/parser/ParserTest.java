package edu.ucsb.cs56.pconrad.parsing.parser;

import edu.ucsb.cs56.pconrad.parsing.tokenizer.*;
import edu.ucsb.cs56.pconrad.parsing.syntax.*;
import java.util.ArrayList;
import static edu.ucsb.cs56.pconrad.parsing.DefaultInterpreterInterface.DEFAULT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.IOException;

/**
 * Tests the code in <code>Parser</code>.
 * Specifically, this ensures that we properly go from lists of <code>Token</code>s
 * to <code>AST</code>s.
 * @see edu.ucsb.cs56.pconrad.parsing.parser.Parser
 * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.Token
 * @see edu.ucsb.cs56.pconrad.parsing.syntax.AST
 */
public class ParserTest {
    // begin instance variables
    private final ASTFactory af;
    // end instance variables

    public ParserTest() {
        af = DefaultASTFactory.DEFAULT;
    }

    /**
      Convenience method to tokenize and parse the given input
      @throws ParserException if there is a parsing error
      @param input The expression to be evaluated, as a string
      @return An abstract syntax tree for that expression

     */
    public static AST parse(final String input)
    throws  ParserException {
        return DEFAULT.tokenizeAndParse(input);
    }

    /**
      Like <code>parse</code>, but it does not throw any annotated
      exceptions.  This is to avoid repeatedly annotating tests to
      throw exceptions.  Internally, if tokenizing or parsing
      <code>input</code> throws
      a <code>ParserException</code>, this will trigger test failure.

      @param input The expression to be evaluated, as a string
      @return An abstract syntax tree for that expression

    */
    public static AST parseNoException(final String input) {
        AST retval = null;

        try {
            retval = parse(input);
        } catch (ParserException e) {
            fail("Unexpected parse exception: " + e.toString());
        }

        assert(retval != null);
        return retval;
    }

    @Test
    public void testParseNum() {
        assertEquals(af.makeLiteral(42),
                     parseNoException("42"));
    }

    @Test
    public void testParseAdd() {
        assertEquals(af.makePlusNode(af.makeLiteral(1),
                                     af.makeLiteral(2)),
                     parseNoException("1 + 2"));
    }

    @Test
    public void testParseMinus() {
        assertEquals(af.makeMinusNode(af.makeLiteral(1),
                                      af.makeLiteral(2)),
                     parseNoException("1 - 2"));
    }


    @Test
    public void testParseParensLiteral() {
        assertEquals(af.makeLiteral(1),
                     parseNoException("(1)"));
    }

    @Test
    public void testParseParensBinop() {
        assertEquals(af.makePlusNode(af.makeLiteral(1),
                                     af.makeLiteral(2)),
                     parseNoException("(1 + 2)"));
    }

    @Test
    public void testPrecedenceHigherFirst() {
        assertEquals(af.makePlusNode(af.makeTimesNode(af.makeLiteral(1),
                                     af.makeLiteral(2)),
                                     af.makeLiteral(3)),
                     parseNoException("1 * 2 + 3"));
    }

    @Test
    public void testPrecedenceParensApplyRight() {
        assertEquals(af.makeTimesNode(af.makeLiteral(1),
                                      af.makePlusNode(af.makeLiteral(2),
                                              af.makeLiteral(3))),
                     parseNoException("1 * (2 + 3)"));
    }

    @Test
    public void testUnaryMinusLiteral() {
        assertEquals(af.makeUnaryMinusNode(af.makeLiteral(42)),
                     parseNoException("-42"));
    }

    @Test
    public void testUnaryMinusNonMinusBinop() {
        assertEquals(af.makePlusNode(af.makeLiteral(2),
                                     af.makeUnaryMinusNode(af.makeLiteral(5))),
                     parseNoException("2 + -5"));
    }

    // BEGIN TESTS FOR INVALID INPUTS
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public AST parseExpectFailure(String input)
    throws ParserException {
        thrown.expect(ParserException.class);
        return parse(input);
    }

    @Test
    public void testInvalidDoubleNumber()
    throws ParserException {
        parseExpectFailure("5 6");
    }

    @Test
    public void testMissingSecondOperand()
    throws ParserException {
        parseExpectFailure("5 +");
    }

    @Test
    public void testMissingSecondOperandInParens()
    throws ParserException {
        parseExpectFailure("(5 +)");
    }


    @Test
    public void testParserTokenAtException()
    throws ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("Attempted to get token out of position");
        ArrayList<Token> emptyTokenList = new ArrayList<Token>();
        Parser p = new Parser(emptyTokenList);
        Token t = p.tokenAt(0);
    }

    @Test
    public void testParserTokenAtException_negative()
    throws ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("Attempted to get token out of position");
        ArrayList<Token> emptyTokenList = new ArrayList<Token>();
        Parser p = new Parser(emptyTokenList);
        Token t = p.tokenAt(-1);
    }


} // ParserTest

