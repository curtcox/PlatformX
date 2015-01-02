package common.screen;

import common.ui.IForm;
import common.ui.UIComponent;
import fake.FakeForm;
import fake.FakeSERegistryLoader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class ScreenTest {

    FakeForm form = new FakeForm();
    String name = random("name");
    UIComponent layout = new UIComponent();

    class ExampleScreen extends Screen {

        public ExampleScreen(IForm form,String name) {
            super(form,name);
        }

        @Override
        protected UIComponent layoutForPortrait() {
            return layout;
        }
    }

    @Before
    public void setup() {
        FakeSERegistryLoader.load();
    }

    @Test
    public void can_create() {
        assertNotNull(new ExampleScreen(form,name));
    }

    @Test
    public void show_sets_form_layout() {
        ExampleScreen testObject = new ExampleScreen(form,name);

        testObject.show();

        assertSame(layout, form.layout);
    }

    @Test
    public void refresh_sets_form_layout() {
        ExampleScreen testObject = new ExampleScreen(form,name);

        testObject.refresh();

        assertSame(layout, form.layout);
    }

    private String random(String name) {
        return name + toString();
    }


}