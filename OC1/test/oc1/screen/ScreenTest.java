package oc1.screen;

import com.codename1.ui.Form;
import fake.FakeRegistryLoader;
import java.util.concurrent.Callable;

import oc1.ui.IForm;
import oc2.screens.FakeUI;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ScreenTest {
    
    IForm form;
    Screen previous;
    
    Screen testObject;
    
    @Before
    public void setUp() {
        FakeRegistryLoader.load();
    }

    @Test
    public void can_create() {
        new Screen(FakeUI.newForm(),"name"){
            @Override protected ScreenLayout layoutForPortrait() { return null;}
        };
    }
    
    @Test
    public void can_create_with_form() throws Exception {
        assertNotNull(createScreenOnEDT(previous));
    }
    
    private Screen createScreenOnEDT(final Screen previous) throws Exception {
        return (Screen) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                form = FakeUI.newForm();
                return new Screen(form,"name"){
                    @Override protected ScreenLayout layoutForPortrait() { return null; }
                };
            }
        });
    }
    
}
