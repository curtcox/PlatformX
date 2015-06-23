package common.page;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScreenLinkTest {

    @Test
    public void uses_values_from_factory_method() {
        String name = random("name");
        String arg = random("arg");
        ScreenLink testObject = ScreenLink.of(name,arg);

        assertEquals(name,testObject.tags.toString());
        assertEquals(name, testObject.title());
        assertEquals(1,testObject.args.length);
        assertSame(arg,testObject.args[0]);
    }

    @Test
    public void are_equal() {
        assertEqual(ScreenLink.of(""), ScreenLink.of(""));
        assertEqual(ScreenLink.of("x"), ScreenLink.of("x"));
    }

    private void assertEqual(ScreenLink a, ScreenLink b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Test
    public void are_not_equal() {
        assertNotEqual(ScreenLink.of("x"), ScreenLink.of("y"));
    }

    private void assertNotEqual(ScreenLink a, ScreenLink b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    private String random(String prefix) {
        return prefix + "@" + hashCode();
    }
}