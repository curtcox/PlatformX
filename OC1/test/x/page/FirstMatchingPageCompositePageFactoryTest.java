package x.page;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FirstMatchingPageCompositePageFactoryTest {

    PageLink link = PageLink.of("target");
    @Test
    public void can_create() {
        assertNotNull(new FirstMatchingPageCompositePageFactory());
    }

    @Test
    public void create_throws_an_exception_when_there_are_no_factories() {
        FirstMatchingPageCompositePageFactory factory = new FirstMatchingPageCompositePageFactory();
        try {
            factory.create(link);
            fail();
        } catch (IllegalArgumentException e) {
            String message = "No screen for " + link + " in " + Arrays.asList();
            assertEquals(message,e.getMessage());
        }
    }
}