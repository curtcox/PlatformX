package c1.screen;


import c1.screens.FakeUI;
import common.screen.Page;
import common.screen.Screen;
import common.screen.ScreenLink;
import common.ui.IForm;
import fake.FakeC1RegistryLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertNotNull;

public class C1ScreenTest {

    ScreenLink link;
    Page page;
    IForm form;
    Screen previous;

    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
    }

    @Test
    public void can_create() {
        new Screen(link,page);
    }
    
    @Test
    public void can_create_with_form() throws Exception {
        assertNotNull(createScreenOnEDT(previous));
    }
    
    private Screen createScreenOnEDT(final Screen previous) throws Exception {
        return (Screen) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                form = FakeUI.newForm();
                return new Screen(link,page);
            }
        });
    }


}
