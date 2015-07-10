package va.ui;

import config.ShouldRun;
import fake.FakeVaRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class VaFormTest {

    String title = random("link");
    PageLink link = PageLink.of(title);
    VaForm testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Vaadin);
        FakeVaRegistryLoader.load();
        testObject = new VaForm(link);
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
        VaForm testObject = new VaForm(link);

        assertSame(link, testObject.getScreenLink());
    }

    @Test
    public void can_show() {
        testObject.show();
    }

    @Test
    public void there_are_0_components_when_none_have_been_added() {
        assertEquals(0, testObject.getComponentCount());
    }

    @Test
    public void layout_is_idempotent() {
        XComponent layout = new XLabel("!!");

        for (int i=0; i<3; i++) {
            testObject.layout(layout);
            assertEquals(1, testObject.getComponentCount());
        }
    }

    @Test
    public void form_is_an_IForm() {
        assertTrue(testObject instanceof IForm);
    }

    private String random(String prefix) {
        return (prefix + toString().toLowerCase());
    }

}