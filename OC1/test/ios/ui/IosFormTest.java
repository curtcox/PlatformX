package ios.ui;

import fake.FakeIosRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import org.robovm.apple.uikit.UIViewController;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;

import static org.junit.Assert.*;

public class IosFormTest {

    String title = random("link");
    PageLink link = PageLink.of(title);
    IosForm testObject;

    @Before
    public void setUp() {
        FakeIosRegistryLoader.load();
        testObject = new IosForm(link);
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
        IosForm testObject = new IosForm(link);

        assertSame(link, testObject.getScreenLink());
    }

    @Test
    public void can_show() {
        testObject.show();
    }

    @Test
    public void there_are_0_components_when_none_have_been_added() {
        assertEquals(0, testObject.getChildViewControllers().size());
    }

    @Test
    public void layout_is_idempotent() {
        XComponent layout = new XLabel("!!");

        for (int i=0; i<3; i++) {
            testObject.layout(layout);
            assertEquals(3, testObject.getChildViewControllers());
        }
    }

    @Test
    public void form_is_a_view() {
        assertTrue(testObject instanceof UIViewController);
    }

    private String random(String prefix) {
        return (prefix + toString().toLowerCase());
    }

}