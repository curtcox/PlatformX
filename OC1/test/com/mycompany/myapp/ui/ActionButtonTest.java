package com.mycompany.myapp.ui;

import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.fake.FakeLocationManager;
import com.mycompany.fake.FakeStorage;
import com.mycompany.myapp.CurrentState;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.event.Change;
import com.mycompany.myapp.screens.FakeUI;
import com.mycompany.myapp.services.Locations;
import com.mycompany.myapp.stores.ServiceProviders;
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
        Registry.put(Storage.class, new FakeStorage());
        Registry.put(LocationManager.class, new FakeLocationManager());
        Registry.put(Locations.class, new Locations());
        Registry.put(ServiceProviders.class, new ServiceProviders());
        Registry.put(CurrentState.class, new CurrentState());

        ActionButton button = createActionButtonOnEDT("");
        String expected = stringSource().getString();
        button.updateTextOnChange(stringSource());
        CurrentState.get().broadcastChange();
        
        FakeUI.flushEDT();
        
        assertEquals(expected,button.getText());
    }

}
