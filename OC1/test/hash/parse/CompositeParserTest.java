package hash.parse;

import hash.Args;
import hash.Invocation;
import hash.StringConstant;
import hash.lex.Tokens;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class CompositeParserTest {
    
    CompositeParser testObject = new CompositeParser(new StringConstantParser(),new InvocationParser());
    
    @Test
    public void canParse_returns_true_if_any_component_can_parse() {
        assertTrue(testObject.canParse(Tokens.from("\"constant\"")));
        assertTrue(testObject.canParse(Tokens.from("method")));
    }

    @Test
    public void canParse_returns_false_if_no_component_can_parse() {
        assertFalse(testObject.canParse(Tokens.from("?")));
        assertFalse(testObject.canParse(Tokens.from(":")));
    }

    @Test
    public void parse_returns_results_from_first_parser_that_can_parse() {
        assertEquals(new StringConstant("constant"),testObject.parse(Tokens.from("\"constant\"")));
        assertEquals(new Invocation("method",new Args()),testObject.parse(Tokens.from("method")));
    }

}
