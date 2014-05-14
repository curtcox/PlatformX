package com.mycompany.myapp.ui;

import com.mycompany.myapp.screens.FakeUI;
import java.util.concurrent.Callable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ActionButtonTest {

    boolean tapped;
    
    private ActionButton createActionButtonOnEDT(final String text) throws Exception {
        return (ActionButton) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return new ActionButton(text) {
                    @Override
                    public void onTap() {
                        tapped = true;
                    }
                };
            }
        });
    }

    @Test
    public void sets_text_to_constructor_value() throws Exception {
        String random = this.toString();
        ActionButton button = createActionButtonOnEDT(random);
        assertSame(random,button.getText());
    }

    @Test
    public void performs_action_when_pressed() throws Exception {
        ActionButton button = createActionButtonOnEDT("");
        button.pressed();
        button.released();
        FakeUI.flushEDT();
        assertTrue(tapped);
    }

}
