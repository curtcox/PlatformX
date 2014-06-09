package oc1.ui;

import oc1.ui.StringSource;
import oc1.ui.ActionButton;
import fake.FakeRegistryLoader;
import com.codename1.ui.events.ActionListener;
import oc1.app.CurrentState;
import oc1.event.Change;
import oc1.screens.FakeUI;
import java.util.concurrent.Callable;
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
    public void updateTextOnChange_with_speciified_source_updates_text() throws Exception {
        ActionButton button = createActionButtonOnEDT("");
        String expected = stringSource().getString();
        Change.Source change = new Change.Source() {
            public void addListener(Change.Listener listener) {
                ActionButtonTest.this.listener = listener;
            }
        };
        button.updateTextOnChange(change, stringSource());
        listener.onChange();
        FakeUI.flushEDT();
        
        assertEquals(expected,button.getText());
    }

    StringSource stringSource() {
        return new StringSource() {
            public String getString() {
                return ActionButtonTest.this.toString();
            }
        };
    }
    
    @Test
    public void updateTextOnChange_updates_text_when_current_state_changes() throws Exception {
        FakeRegistryLoader.load();
        ActionButton button = createActionButtonOnEDT("");
        String expected = stringSource().getString();
        button.updateTextOnChange(stringSource());
        CurrentState.get().broadcastChange();
        
        FakeUI.flushEDT();
        
        assertEquals(expected,button.getText());
    }

}
