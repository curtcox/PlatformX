package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class CompositeParserTest {
    
    CompositeParser testObject = new CompositeParser(new Constant.Parser(),new Invocation.Parser());
    
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
        assertEquals(new Constant("constant"),testObject.parse(Tokens.from("\"constant\"")));
        assertEquals(new Invocation("method"),testObject.parse(Tokens.from("method")));
    }

}
