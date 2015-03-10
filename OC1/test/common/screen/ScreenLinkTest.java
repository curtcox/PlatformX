package common.screen;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ScreenLinkTest {

    @Test
    public void uses_values_from_factory_method() {
        String name = random("name");
        String arg = random("arg");
        ScreenLink testObject = ScreenLink.of(name,arg);

        assertSame(name,testObject.screen);
        assertEquals(1,testObject.args.length);
        assertSame(arg,testObject.args[0]);
    }

    private String random(String prefix) {
        return prefix + toString();
    }
}