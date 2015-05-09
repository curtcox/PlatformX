package c1.ui;

import common.screen.ScreenLink;
import common.ui.IForm;
import fake.FakeC1RegistryLoader;
import fake.FakeUIManager;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class C1FormTest {

    String title = random("link");
    ScreenLink link = ScreenLink.of(title);
    C1Form testObject;

    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
        FakeUIManager.of();
        testObject = new C1Form(link);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void is_IForm() {
        assertTrue(testObject instanceof IForm);
    }

    @Test
    public void title_is_set_from_constructor() {
        assertEquals(title, testObject.getTitle());
    }

    @Test
    public void link_is_set_from_constructor() {
        C1Form testObject = new C1Form(link);

        assertSame(link, testObject.getScreenLink());
    }

    @Test
    public void can_show() {
        testObject.show();
    }

    private String random(String prefix) {
        return (prefix + toString().toLowerCase());
    }

}