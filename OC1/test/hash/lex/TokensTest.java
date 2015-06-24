package hash.lex;

import x.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

public class TokensTest {
    
    @Test
    public void from_creates_empty_tokens_for_empty_string() {
        Tokens tokens = Tokens.from("");
        assertFalse(tokens.hasNext());
    }

    @Test
    public void from_creates_empty_tokens_for_empty_comment_line() {
        Tokens tokens = Tokens.from("#");
        assertFalse(tokens.hasNext());
    }

    @Test
    public void from_creates_empty_tokens_for_comment_line_with_comment() {
        Tokens tokens = Tokens.from("# line with only a comment");
        assertFalse(tokens.hasNext());
    }

    @Test
    public void from_creates_token_with_one_element_for_one_element() {
        Tokens tokens = Tokens.from("one");
        assertTrue(tokens.hasNext());
        assertEquals("one",tokens.next());
        assertFalse(tokens.hasNext());
    }

    @Test
    public void from_creates_token_with_one_element_for_one_element_with_comment() {
        Tokens tokens = Tokens.from("one#");
        assertTrue(tokens.hasNext());
        assertEquals("one",tokens.next());
        assertFalse(tokens.hasNext());
    }

    @Test
    public void peek_returns_one_element_without_removing_it() {
        Tokens tokens = Tokens.from("one");
        assertTrue(tokens.hasNext());
        assertEquals("one",tokens.peek());
        assertTrue(tokens.hasNext());
    }

    @Test
    public void nextIs_returns_true_if_string_matches_next_token() {
        Tokens tokens = Tokens.from("one");
        assertTrue(tokens.nextIs("one"));
    }

    @Test
    public void nextIs_returns_false_if_there_are_no_more_tokens() {
        Tokens tokens = Tokens.from("");
        assertFalse(tokens.nextIs(""));
    }

    @Test
    public void nextIs_returns_false_if_string_doesnt_matches_next_token() {
        Tokens tokens = Tokens.from("one");
        assertFalse(tokens.nextIs("thing"));
    }

    @Test
    public void nextIs_consumes_token() {
        Tokens tokens = Tokens.from("one");
        tokens.nextIs("one");
        assertFalse(tokens.hasNext());
    }

    @Test
    public void peekIs_returns_false_if_there_are_no_more_tokens() {
        Tokens tokens = Tokens.from("");
        assertFalse(tokens.peekIs(""));
    }

    @Test
    public void peekIs_returns_false_if_string_doesnt_matches_next_token() {
        Tokens tokens = Tokens.from("one");
        assertFalse(tokens.peekIs("thing"));
    }

    @Test
    public void peekIs_doesn_not_consume_token() {
        Tokens tokens = Tokens.from("one");
        tokens.peekIs("one");
        assertTrue(tokens.hasNext());
    }

    @Test
    public void copy_produces_equivalent_tokens() {
        Tokens tokens = Tokens.from("one");
        Tokens copy = tokens.copy();
        
        assertTrue(copy.hasNext());
        assertEquals("one",copy.next());
        assertFalse(copy.hasNext());
    }

    @Test
    public void changes_to_copy_do_not_change_original() {
        Tokens tokens = Tokens.from("one");

        Tokens copy = tokens.copy();
        copy.next();
        
        assertTrue(tokens.hasNext());
        assertEquals("one",tokens.next());
        assertFalse(tokens.hasNext());
    }

    @Test
    public void toString_contains_string() {
        assertTrue(Strings.contains(Tokens.from("nuts").toString(),"nuts"));
    }

    @Test
    public void toString_contains_strings_separated_by_spaces() {
        assertTrue(Strings.contains(Tokens.from("tinker evars chance").toString(),"tinker evars chance"));
    }

    @Test
    public void toString_contains_original_tokens() {
        assertTrue(Strings.contains(Tokens.from("f{}").toString(),"f { }"));
    }

    @Test
    public void toStrings_contains_token_strings() {
        Tokens tokens = Tokens.from("one two");
        String[] actual = tokens.toStrings();
        assertEquals(2,actual.length);
        assertEquals("one",actual[0]);
        assertEquals("two",actual[1]);
    }

}
