package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ExpressionTest {
    
    @Test
    public void parse_returns_correct_value_for_constant() {
        parse(new Constant(""),"\"\"");
        parse(new Constant("foo"),"\"foo\"");
    }

    @Test
    public void parse_returns_correct_value_for_invocation() {
        parse(new Invocation("doit"),"doit");
    }
    
    private void parse(Expression expression,String string) {
        assertEquals(expression,new Expression.Parser().parse(Tokens.from(string)));
    }
}
