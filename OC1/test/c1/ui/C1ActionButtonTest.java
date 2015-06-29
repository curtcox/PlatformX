package c1.ui;

import fake.FakeC1RegistryLoader;
import fake.FakeUI;
import org.junit.Before;
import org.junit.Test;
import x.app.CurrentState;
import x.event.Change;
import x.event.StringSource;
import x.uiwidget.XButton;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class C1ActionButtonTest {

    boolean tapped;
    Change.Listener listener;
    
    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
    }
    
    private XButton createActionButtonOnEDT(final String text) throws Exception {
        return (XButton) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return new XButton(text) {
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
        XButton button = createActionButtonOnEDT(random);
        assertSame(random, button.getText());
    }

    @Test
    public void updateTextOnChange_with_specified_source_updates_text() throws Exception {
        XButton button = createActionButtonOnEDT("");
        String expected = stringSource().getString();
        Change.Source change = new Change.Source() {
            public void addListener(Change.Listener listener) {
                C1ActionButtonTest.this.listener = listener;
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
                return C1ActionButtonTest.this.toString();
            }
        };
    }
    
    @Test
    public void updateTextOnChange_updates_text_when_current_state_changes() throws Exception {
        FakeC1RegistryLoader.load();
        XButton button = createActionButtonOnEDT("");
        String expected = stringSource().getString();
        button.updateTextOnChange(stringSource());
        CurrentState.get().broadcastChange();
        
        FakeUI.flushEDT();
        
        assertEquals(expected,button.getText());
    }

}
