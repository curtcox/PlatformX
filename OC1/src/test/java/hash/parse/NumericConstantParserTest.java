package hash.parse;

import config.ShouldRun;
import hash.*;
import hash.lex.Tokens;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class NumericConstantParserTest {
    
    NumericConstantParser parser = new NumericConstantParser();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

    private void assertAreEqual(NumericConstant a, NumericConstant b) {
        assertEquals(a,b);
        assertEquals(b,a);
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Test
    public void parse_returns_correct_value() {
        parse(new NumericConstant(7),"7");
        parse(new NumericConstant(42),"42");
        parse(new NumericConstant(63130),"63130");
    }

    @Test
    public void can_parse_constants() {
        assertTrue(parser.canParse(Tokens.from("9")));
        assertTrue(parser.canParse(Tokens.from("10")));
    }

    @Test
    public void can_not_parse_non_constants() {
        assertFalse(parser.canParse(Tokens.from("red")));
        assertFalse(parser.canParse(Tokens.from("?")));
        assertFalse(parser.canParse(Tokens.from("}")));
        assertFalse(parser.canParse(Tokens.from("{")));
    }
    
    private void parse(NumericConstant constant,String string) {
        assertAreEqual(constant,parser.parse(Tokens.from(string)));
    }
    
    @Test
    public void invokeIn_returns_constant_value() {
        assertEquals(7L,new NumericConstant(7).invokeIn(null));
        assertEquals(31415926L,new NumericConstant(31415926).invokeIn(null));
    }

}
