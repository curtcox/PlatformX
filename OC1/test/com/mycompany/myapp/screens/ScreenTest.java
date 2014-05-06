package com.mycompany.myapp.screens;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.fake.FakeUIManager;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class ScreenTest {
    
    Form form;
    Screen previous;
    
    Screen testObject;
    
    @Test
    public void can_create() {
        new Screen(form,previous){};
    }
    
    @Test
    public void can_create_with_form() throws Exception {
        assertNotNull(createScreenOnEDT(previous));
    }
    
    private Screen createScreenOnEDT(final Screen previous) throws Exception {
        return (Screen) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                form = FakeUI.newForm();
                return new Screen(form,previous){};
            }
        });
    }
    
}
