package hash.parse;

import hash.*;
import hash.lex.Tokens;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ReturnParserTest {
    
    private void assertEquals(Return a, Return b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.hashCode()==b.hashCode());
    }

    @Test
    public void parse_returns_correct_value_for_constants() {
        parse(new Return(new StringConstant("")),"^ \"\"");
        parse(new Return(new StringConstant("foo")),"^ \"foo\"");
    }

    @Test
    public void parse_returns_correct_value_for_invocations() {
        parse(new Return(new Invocation("foo")),"^ foo");
    }

    @Test
    public void canParse_returns_true_when_starts_with_return() {
        assertTrue(canParse("^ foo"));
    }

    @Test
    public void canParse_returns_false_when_does_not_start_with_return() {
        assertFalse(canParse(""));
        assertFalse(canParse("?"));
        assertFalse(canParse(":"));
    }
    
    private void parse(Return ret,String string) {
        assertEquals(ret,new ReturnParser().parse(Tokens.from(string)));
    }

    private boolean canParse(String string) {
        return new ReturnParser().canParse(Tokens.from(string));
    }

}
