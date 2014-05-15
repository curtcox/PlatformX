package com.mycompany.myapp.ui;

import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.event.Change;
import com.mycompany.myapp.screens.FakeUI;
import java.util.concurrent.Callable;
import static jdk.nashorn.internal.objects.NativeRegExp.source;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class ActionButtonTest {

    boolean tapped;
    Change.Listener listener;
    
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
        ActionListener buttonListener = (ActionListener) button.getActionListeners().get(0);
        buttonListener.actionPerformed(null);
        assertTrue(tapped);
    }

    @Test
    public void updateTextOnChange_updates_text() throws Exception {
        ActionButton button = createActionButtonOnEDT("");
        final String newValue = "new";
        Change.Source change = new Change.Source() {
            public void addListener(Change.Listener listener) {
                ActionButtonTest.this.listener = listener;
            }
        };
        StringSource source = new StringSource() {
            public String getString() {
                return newValue;
            }
        };
        button.updateTextOnChange(change, source);
        listener.onChange();
        FakeUI.flushEDT();
        
        assertEquals(newValue,button.getText());
    }

}
