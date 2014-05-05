package com.mycompany.myapp.screens;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.mycompany.fake.FakeUIManager;
import com.codename1.ui.plaf.UIManager;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class ScreenTest {
    
    @Test
    public void can_create() {
        Form form = null;
        Screen previous = null;
        new Screen(form,previous){};
    }
    
    @Test
    public void can_create_with_form() {
        Runnable runnable = new Runnable() {
            public void run() {
                Form form = newForm();
                Screen previous = null;
                new Screen(form,previous){};
            }
        };
        display().invokeAndBlock(runnable);
    }
    
    static Display display() {
        return Display.getInstance();
    }
    
    private static Form newForm() {
        return new Form() {
            @Override
            public UIManager getUIManager(){
                return FakeUIManager.of();
            }
        };
    }
    
}
