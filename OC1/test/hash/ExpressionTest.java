package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ExpressionTest {
    
    @Test
    public void parse_returns_correct_value() {
        parse(new Constant(""),"\"\"");
        parse(new Constant("foo"),"\"foo\"");
    }
    
    private void parse(Constant constant,String string) {
        assertEquals(constant,new Expression.Parser().parse(Tokens.from(string)));
    }
}
