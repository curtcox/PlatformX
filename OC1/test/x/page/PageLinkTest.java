package x.page;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageLinkTest {

    @Test
    public void uses_values_from_factory_method() {
        String name = random("name");
        String arg = random("arg");
        PageLink testObject = PageLink.of(name, arg);

        assertEquals(name,testObject.tags.toString());
        assertEquals(name, testObject.title());
        assertEquals(1,testObject.args.length);
        assertSame(arg,testObject.args[0]);
    }

    @Test
    public void are_equal() {
        assertEqual(PageLink.of(""), PageLink.of(""));
        assertEqual(PageLink.of("x"), PageLink.of("x"));
    }

    private void assertEqual(PageLink a, PageLink b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Test
    public void are_not_equal() {
        assertNotEqual(PageLink.of("x"), PageLink.of("y"));
    }

    private void assertNotEqual(PageLink a, PageLink b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    private String random(String prefix) {
        return prefix + "@" + hashCode();
    }
}