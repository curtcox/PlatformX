package c1.ui;

import common.screen.ScreenLink;
import common.ui.IForm;
import fake.FakeC1RegistryLoader;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class C1FormTest {

    String title = random("link");
    ScreenLink link = ScreenLink.of(title);

    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
    }

    @Test
    public void can_create() {
        assertNotNull(new C1Form(ScreenLink.of("")));
    }

    @Test
    public void is_IForm() {
        assertTrue(new C1Form(ScreenLink.of("")) instanceof IForm);
    }

    @Test
    public void title_is_set_from_constructor() {
        C1Form testObject = new C1Form(link);

        assertEquals(title, testObject.getTitle());
    }

    @Test
    public void link_is_set_from_constructor() {
        C1Form testObject = new C1Form(link);

        assertSame(link, testObject.getScreenLink());
    }

    private String random(String prefix) {
        return (prefix + toString().toLowerCase());
    }

}