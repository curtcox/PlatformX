package common.screen;

import common.ui.IForm;
import common.uiwidget.UIComponent;
import fake.FakeForm;
import fake.FakeSERegistryLoader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * I'm ready for my closeup.
 */
public class ScreenTest {

    FakeForm form = new FakeForm();
    String name = random("link");
    UIComponent layout = new UIComponent();
    ScreenLink link = ScreenLink.of(name);
    ExampleScreen testObject;

    class ExampleScreen extends Screen {

        public ExampleScreen(IForm form,ScreenLink link) {
            super(form,link);
        }

        @Override
        protected UIComponent layoutForPortrait() {
            return layout;
        }

        @Override
        public String toString() {
            return link.toString();
        }
    }

    @Before
    public void setup() {
        FakeSERegistryLoader.load();
        testObject = new ExampleScreen(form,link);
    }


    @Test
    public void can_create() {
        assertNotNull(new ExampleScreen(form,link));
    }

    @Test
    public void uses_screenLink_from_constructor() {
        assertSame(link,testObject.link);
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
        ExampleScreen first = new ExampleScreen(new FakeForm(),ScreenLink.of("first"));
        ExampleScreen second = new ExampleScreen(new FakeForm(),ScreenLink.of("second"));

        first.show();
        second.show();

        second.back();

        assertSame(first,Screen.getShowing());
    }

    @Test
    public void back_shows_previously_shown_screen() {
        FakeForm form1 = new FakeForm();
        ExampleScreen first = new ExampleScreen(form1,ScreenLink.of("first"));
        ExampleScreen second = new ExampleScreen(new FakeForm(),ScreenLink.of("second"));

        first.show();
        second.show();

        second.back();

        assertTrue(form1.showBackWasCalled);
    }

    @Test
    public void setBackCommand_is_called_when_there_is_a_previous_screen() {
        FakeForm form1 = new FakeForm();
        FakeForm form2 = new FakeForm();
        ExampleScreen first = new ExampleScreen(form1,ScreenLink.of("first"));
        ExampleScreen second = new ExampleScreen(form2,ScreenLink.of("second"));

        first.show();
        second.show();

        second.back();

        assertEquals("Back", form2.getBackCommand().command);
    }

    private String random(String name) {
        return name + toString();
    }


}