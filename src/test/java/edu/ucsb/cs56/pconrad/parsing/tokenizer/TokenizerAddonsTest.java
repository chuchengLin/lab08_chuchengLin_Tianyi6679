package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TokenizerAddonsTest {

    private TokenFactory tf = new DefaultTokenFactory();


    @Test
    public void testLessThanTokenToString() {
        assertEquals("LTToken(<)", tf.makeLessThanToken().toString());
    }

    @Test
    public void testLessThanOrEqualsTokenToString() {
        assertEquals("LEToken(<=)", tf.makeLessThanOrEqualsToken().toString());
    }

    @Test
    public void testGreaterThanTokenToString() {
        assertEquals("GTToken(>)", tf.makeGreaterThanToken().toString());
    }

    @Test
    public void testGreaterThanOrEqualsTokenToString() {
        assertEquals("GEToken(>=)", tf.makeGreaterThanOrEqualsToken().toString());
    }

    @Test
    public void testEqualsTokenToString() {
        assertEquals("EQToken(==)", tf.makeEqualsToken().toString());
    }

    @Test
    public void testNotEqualsTokenToString() {
        assertEquals("NEToken(!=)", tf.makeNotEqualsToken().toString());
    }

    @Test

    public void testExponentTokenToString() {
        assertEquals("ExponentToken", tf.makeExponentToken().toString());
    }

    @Test
    public void testOneExponentToken() {
        assertArrayEquals(new Token[] { tf.makeExponentToken() },
                          Tokenizer.tokenizeToArray("**"));
    }

    @Test
    public void testTokenizeOneTimesTwo() {
        assertArrayEquals(new Token[] {
                              tf.makeIntToken(1),
                              tf.makeTimesToken(),
                              tf.makeIntToken(2)
                          },
                          Tokenizer.tokenizeToArray("1*2"));
    }

    @Test
    public void testTokenizeThreeToTheFourth() {
        assertArrayEquals(new Token[] {
                              tf.makeIntToken(3),
                              tf.makeExponentToken(),
                              tf.makeIntToken(4)
                          },
                          Tokenizer.tokenizeToArray("3**4"));
    }

    @Test
    public void testOneEqualsToken() {
        assertArrayEquals(new Token[] { tf.makeEqualsToken() },
                          Tokenizer.tokenizeToArray("=="));
    }

    @Test
    public void testTwoEqualsTokensWithWhiteSpace() {
        assertArrayEquals(new Token[] {
                              tf.makeEqualsToken(),
                              tf.makeEqualsToken()
                          },
                          Tokenizer.tokenizeToArray("== =="));
    }

    @Test
    public void testTwoEqualsTokensNoWhiteSpace() {
        assertArrayEquals(new Token[] {
                              tf.makeEqualsToken(),
                              tf.makeEqualsToken()
                          },
                          Tokenizer.tokenizeToArray("===="));
    }

    @Test
    public void testOneEqualsTokensOneErrorEqualToken() {
        assertArrayEquals(new Token[] {
                              tf.makeEqualsToken(),
                              tf.makeErrorToken("=")
                          },
                          Tokenizer.tokenizeToArray("==="));
    }

    @Test
    public void testEqualsExpression() {
        assertArrayEquals(new Token[] { tf.makeIntToken("12"),
                                        tf.makeEqualsToken(),
                                        tf.makeIntToken("13")
                                      },
                          Tokenizer.tokenizeToArray("12 == 13"));
    }

    @Test
    public void testNotEqualsToken() {
        assertArrayEquals(new Token[] { tf.makeNotEqualsToken() },
                          Tokenizer.tokenizeToArray("!="));
    }

    @Test
    public void testNotEqualsExpression() {
        assertArrayEquals(new Token[] { tf.makeIntToken("12"),
                                        tf.makeNotEqualsToken(),
                                        tf.makeIntToken("13")
                                      },
                          Tokenizer.tokenizeToArray("12 != 13"));
    }

    @Test
    public void testLeExpression() {
        assertArrayEquals(new Token[] { tf.makeIntToken("12"),
                                        tf.makeLessThanOrEqualsToken(),
                                        tf.makeIntToken("13")
                                      },
                          Tokenizer.tokenizeToArray("12 <= 13"));
    }

    @Test
    public void testLtExpression() {
        assertArrayEquals(new Token[] { tf.makeIntToken("1"),
                                        tf.makePlusToken(),
                                        tf.makeIntToken("2"),
                                        tf.makeLessThanToken(),
                                        tf.makeIntToken("3"),
                                        tf.makeTimesToken(),
                                        tf.makeIntToken("4")
                                      },
                          Tokenizer.tokenizeToArray("1+2<3*4"));
    }


    @Test
    public void testGtExpression() {
        assertArrayEquals(new Token[] { tf.makeIntToken("12"),
                                        tf.makeGreaterThanToken(),
                                        tf.makeIntToken("13")
                                      },
                          Tokenizer.tokenizeToArray("12 > 13"));
    }

    @Test
    public void testGeExpression() {
        assertArrayEquals(new Token[] { tf.makeLParenToken(),
                                        tf.makeIntToken("78"),
                                        tf.makeMinusToken(),
                                        tf.makeIntToken("90"),
                                        tf.makeRParenToken(),
                                        tf.makeGreaterThanOrEqualsToken(),
                                        tf.makeIntToken("13"),
                                        tf.makeDivideToken(),
                                        tf.makeIntToken("4")
                                      },
                          Tokenizer.tokenizeToArray("(78-90)>=13/4"));
    }

    public void testExponentOperator() {
        assertArrayEquals(new Token[] {
                              tf.makeIntToken("2"),
                              tf.makeExponentToken(),
                              tf.makeIntToken("16"),
                          },
                          Tokenizer.tokenizeToArray("2**16"));
    }

    public void testInequalityOperators() {
        assertArrayEquals(new Token[] {
                              tf.makeLessThanToken(),
                              tf.makeLessThanOrEqualsToken(),
                              tf.makeLessThanToken(),
                              tf.makeGreaterThanToken(),
                              tf.makeLessThanOrEqualsToken(),
                          },
                          Tokenizer.tokenizeToArray("<<=<>>="));
    }

    public void brokenInequalityOperators() {
        assertArrayEquals(new Token[] {
                              tf.makeLessThanToken(),
                              tf.makeErrorToken("="),
                              tf.makeGreaterThanToken(),
                              tf.makeErrorToken("="),
                              tf.makeEqualsToken(),
                              tf.makeErrorToken("="),
                              tf.makeErrorToken("="),
                          },
                          Tokenizer.tokenizeToArray("< = > = == = = "));
    }

}
