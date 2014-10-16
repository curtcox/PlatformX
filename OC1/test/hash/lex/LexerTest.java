package hash.lex;

import org.junit.Test;
import static org.junit.Assert.*;

public class LexerTest {
    
    @Test
    public void split_returns_proper_parts() {
        split("");    
        split("{}","{","}");    
    }

    @Test
    public void split_commas() {
        split(",,",",",",");    
    }

    @Test
    public void split_pointers() {
        split("^^","^","^");    
    }

    @Test
    public void split_returns_two_double_quotes_with_no_content_as_one_token() {
        split("\"\"","\"\"");    
    }

    @Test
    public void split_returns_two_double_quotes_with_content_as_one_token() {
        split("\"stuff\"","\"stuff\"");    
    }

    @Test
    public void split_returns_two_double_quotes_with_content_and_spaces_as_one_token() {
        split("\"N E\"","\"N E\"");    
    }

    @Test
    public void split_questionmark_and_colon() {
        split("?:","?",":");    
    }

    @Test
    public void split_ternary() {
        split("(condition)?one:two","(","condition",")","?","one",":","two");    
    }

    @Test
    public void split_empty_string() {
        split("");    
    }

    @Test
    public void split_empty_brackets() {
        split("{}","{","}");    
    }

    @Test
    public void split_empty_parens() {
        split("()","(",")");    
    }

    @Test
    public void split_a_word() {
        split("word","word");    
    }

    @Test
    public void split_words_with_single_spaces_between_them() {
        split("one two three","one","two","three");    
    }

    @Test
    public void split_words_with_double_spaces_between_them() {
        split("one  two  three","one","two","three");    
    }

    @Test
    public void split_words_with_carriage_returns_between_them() {
        split("one\rtwo\rthree","one","two","three");    
    }

    @Test
    public void split_words_with_new_lines_between_them() {
        split("one\ntwo\nthree","one","two","three");    
    }

    @Test
    public void split_words_with_tabs_between_them() {
        split("one\ntwo\nthree","one","two","three");    
    }

    @Test
    public void split_words_with_single_dots_between_them() {
        split("one.two.three","one",".","two",".","three");    
    }

    private void split(String original,String... expected) {
        String[] actual = Lexer.split(original);
        assertEquals(expected.length,actual.length);
        for (int i=0; i<expected.length; i++) {
            assertEquals(expected[i],actual[i]);
        }
    }
}
