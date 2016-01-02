package hash.parse;

import config.ShouldRun;
import hash.*;
import hash.lex.Tokens;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class TernaryParserTest {
    
    TernaryParser parser;
    
    private void assertAreEqual(Ternary a, Ternary b) {
        assertEquals(a,b);
        assertEquals(b,a);
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
        parser =  new TernaryParser();
    }

    @Test
    public void canParse_ternary_with_all_invocations() {
        assertTrue(canParse("(a) ? b : c"));
    }

    @Test
    public void canParse_method_with_constant_alternatives() {
        assertTrue(canParse("(pink) ? \"red\" : \"green\""));
    }

    @Test
    public void parse_returns_correct_value_for_all_invocations() {
        parse(new Ternary(new Invocation("a"),new Invocation("b"),new Invocation("c")),"(a) ? b : c");
    }

    @Test
    public void parse_returns_correct_value_for_constant_alternatives() {
        parse(new Ternary(new Invocation("a"),new StringConstant("b"),new StringConstant("c")),"(a) ? \"b\" : \"c\"");
    }

    private void parse(Ternary ternary,String string) {
        assertAreEqual(ternary,parser.parse(Tokens.from(string)));
    }

    private boolean canParse(String string) {
        return parser.canParse(Tokens.from(string));
    }

}
