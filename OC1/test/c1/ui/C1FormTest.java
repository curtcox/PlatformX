package c1.ui;

import com.codename1.ui.layouts.FlowLayout;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.UIComponent;
import x.uiwidget.UILabel;
import fake.FakeC1RegistryLoader;
import fake.FakeUIManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class C1FormTest {

    String title = random("link");
    PageLink link = PageLink.of(title);
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

    @Test
    public void there_are_2_components_when_none_have_been_added() {
        assertEquals(2,testObject.getComponentCount());
    }

    @Test
    public void layout_is_idempotent() {
        UIComponent layout = new UILabel("!!");

        for (int i=0; i<3; i++) {
            testObject.layout(layout);
            assertEquals(2, testObject.getComponentCount());
        }
    }

    @Test
    public void layout_uses_flow_layout() {
        testObject.layout(new UILabel());
        assertTrue(testObject.getLayout() instanceof FlowLayout);
    }

    private String random(String prefix) {
        return (prefix + toString().toLowerCase());
    }

}