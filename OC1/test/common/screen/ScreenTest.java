package common.screen;

import common.ui.IForm;
import common.uiwidget.UIComponent;
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
    ExampleScreen testObject;

    class ExampleScreen extends Screen {

        public ExampleScreen(IForm form,String name) {
            super(form,name);
        }

        @Override
        protected UIComponent layoutForPortrait() {
            return layout;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Before
    public void setup() {
        FakeSERegistryLoader.load();
        testObject = new ExampleScreen(form,name);
    }

    @Test
    public void can_create() {
        assertNotNull(new ExampleScreen(form,name));
    }

    @Test
    public void show_sets_form_layout() {
        testObject.show();

        assertSame(layout, form.layout);
    }

    @Test
    public void refresh_sets_form_layout() {
        testObject.refresh();

        assertSame(layout, form.layout);
    }

    @Test
    public void show_sets_showing_screen() {
        testObject.show();

        assertSame(testObject,Screen.getShowing());
    }

    @Test
    public void back_shows_previously_shown_screen() {
        FakeForm form1 = new FakeForm();
        FakeForm form2 = new FakeForm();
        ExampleScreen first = new ExampleScreen(form1,"first");
        ExampleScreen second = new ExampleScreen(form2,"second");

        first.show();
        second.show();

        second.back();

        assertSame(first,Screen.getShowing());
    }

    private String random(String name) {
        return name + toString();
    }


}