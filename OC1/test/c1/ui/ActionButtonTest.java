package c1.ui;

import common.event.StringSource;
import common.uiwidget.UIButton;
import fake.FakeC1RegistryLoader;
import common.app.CurrentState;
import common.event.Change;
import c1.screens.FakeUI;
import java.util.concurrent.Callable;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ActionButtonTest {

    boolean tapped;
    Change.Listener listener;
    
    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
    }
    
    private UIButton createActionButtonOnEDT(final String text) throws Exception {
        return (UIButton) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return new UIButton(text) {
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
        UIButton button = createActionButtonOnEDT(random);
        assertSame(random, button.getText());
    }

    @Test
    public void updateTextOnChange_with_specified_source_updates_text() throws Exception {
        UIButton button = createActionButtonOnEDT("");
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
        FakeC1RegistryLoader.load();
        UIButton button = createActionButtonOnEDT("");
        String expected = stringSource().getString();
        button.updateTextOnChange(stringSource());
        CurrentState.get().broadcastChange();
        
        FakeUI.flushEDT();
        
        assertEquals(expected,button.getText());
    }

}
