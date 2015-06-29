package x.uilist;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringToListFilterTest {

    @Test
    public void DEFAULT_filter_passes_all_for_empty_string() {
        StringToListFilter filter = StringToListFilter.DEFAULT;
        ListFilter listFilter = filter.listFilterFor("");
        assertTrue(listFilter.passes(""));
        assertTrue(listFilter.passes("a"));
        assertTrue(listFilter.passes("b"));
        assertTrue(listFilter.passes("c"));
    }

    @Test
    public void DEFAULT_with_one_character_only_passes_strings_with_it() {
        StringToListFilter filter = StringToListFilter.DEFAULT;
        ListFilter listFilter = filter.listFilterFor("q");
        assertFalse(listFilter.passes(""));
        assertFalse(listFilter.passes("a"));
        assertTrue(listFilter.passes("q"));
    }

}