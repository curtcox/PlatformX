package oc1.screen;

import fake.FakeRegistryLoader;
import oc1.event.StringSource;
import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Curt
 */
public class DynamicScreenLayoutTest {

    String hashSourceCode;
    ScreenContext context = new ScreenContext();
    StringSource source = new StringSource() {
        public String getString() {
            return hashSourceCode;
        }
    };
    DynamicScreenLayout testObject = new DynamicScreenLayout(source);
    
    @Before
    public void setUp() {
        FakeRegistryLoader.load();
    }

    @Test
    public void getLayout_returns_layout_with_message_when_source_is_null() {
        ScreenLayout actual = testObject.getLayout(context);
        assertTrue(Strings.contains(actual.components[0].toString(),"Source is not valid Hash"));
    }

    @Test
    public void getLayout_returns_layout_with_message_when_source_is_empty() {
        hashSourceCode = "";
        ScreenLayout actual = testObject.getLayout(context);
        assertTrue(Strings.contains(actual.components[0].toString(),"Source is not valid Hash"));
    }

    @Test
    public void getLayout_returns_layout_with_message_when_layout_not_defined() {
        hashSourceCode = "layover {}";
        ScreenLayout actual = testObject.getLayout(context);
        String string = actual.components[0].toString();
        assertTrue(string,Strings.contains(string,"layout not found"));
    }
    
}
