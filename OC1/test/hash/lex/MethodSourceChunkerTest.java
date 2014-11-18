package hash.lex;

import org.junit.Test;
import static org.junit.Assert.*;

public class MethodSourceChunkerTest {
    
    @Test
    public void split_returns_method_itself_for_single_valid_method() {
        splitsAs("f{}");    
    }

    @Test
    public void split_returns_method_itself_for_single_valid_method_with_whitespace() {
        splitsAs("f { }");    
    }

    @Test
    public void split_returns_2_valid_methods() {
        splitsAs("f{}","f{}");    
    }

    private void splitsAs(String... parts) {
        StringBuilder all = new StringBuilder();
        for (String part : parts) {
            all.append(part);
        }
        String source = all.toString();
        assertEqualArrays(parts,MethodSourceChunker.split(source));
    }

    private void assertEqualArrays(String[] parts, String[] split) {
        assertEquals(parts.length,split.length);
        for (int i=0; i<parts.length; i++) {
            assertEquals(parts[i],split[i]);
        }
    }
    
}
