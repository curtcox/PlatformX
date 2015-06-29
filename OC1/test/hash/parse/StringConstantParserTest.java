package hash.parse;

import hash.*;
import hash.lex.Tokens;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringConstantParserTest {
    
    StringConstantParser parser = new StringConstantParser();
    
    private void assertAreEqual(StringConstant a, StringConstant b) {
        assertEquals(a,b);
        assertEquals(b,a);
        assertTrue(a.hashCode()==b.hashCode());
    }

    @Test
    public void parse_returns_correct_value() {
        parse(new StringConstant(""),"\"\"");
        parse(new StringConstant("foo"),"\"foo\"");
    }

    @Test
    public void can_parse_constants() {
        assertTrue(parser.canParse(Tokens.from("\"\"")));
        assertTrue(parser.canParse(Tokens.from("\"red\"")));
    }

    @Test
    public void can_not_parse_non_constants() {
        assertFalse(parser.canParse(Tokens.from("red")));
        assertFalse(parser.canParse(Tokens.from("?")));
        assertFalse(parser.canParse(Tokens.from("}")));
        assertFalse(parser.canParse(Tokens.from("{")));
    }
    
    private void parse(StringConstant constant,String string) {
        assertAreEqual(constant,parser.parse(Tokens.from(string)));
    }
}
