package hash.lex;

import config.ShouldRun;
import hash.HashLines;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class MethodSourceChunkerTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

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

    @Test
    public void split_does_not_consider_a_chunk_to_end_when_end_is_in_a_comment() {
        String source = HashLines.from("f { # fake end}","real end}");
        String[] parts = MethodSourceChunker.split(source);
        assertEquals(1,parts.length);
        assertEquals("f { # fake end} \r\nreal end}",parts[0]);
    }

    @Test
    public void split_returns_no_lines_for_just_a_comment_line() {
        String source = "#just a comment}";
        String[] parts = MethodSourceChunker.split(source);
        assertEquals(0,parts.length);
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
