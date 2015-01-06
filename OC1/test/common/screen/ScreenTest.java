package common.screen;

import common.ui.IForm;
import common.uiwidget.UIComponent;
import fake.FakeForm;
import fake.FakeSERegistryLoader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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
    public void show_calls_show_on_form() {
        testObject.show();

        assertTrue(form.showWasCalled);
    }

    @Test
    public void getShowing_returns_original_screen_after_going_back_to_it() {
        ExampleScreen first = new ExampleScreen(new FakeForm(),"first");
        ExampleScreen second = new ExampleScreen(new FakeForm(),"second");

        first.show();
        second.show();

        second.back();

        assertSame(first,Screen.getShowing());
    }

    @Test
    public void back_shows_previously_shown_screen() {
        FakeForm form1 = new FakeForm();
        ExampleScreen first = new ExampleScreen(form1,"first");
        ExampleScreen second = new ExampleScreen(new FakeForm(),"second");

        first.show();
        second.show();

        second.back();

        assertTrue(form1.showBackWasCalled);
    }

    private String random(String name) {
        return name + toString();
    }


}