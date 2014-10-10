package oc1.screen;

import com.codename1.ui.Label;
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

    @Test
    public void getLayout_returns_layout_with_label_when_layout_returns_a_string() {
        hashSourceCode = lines("layout { ^'Whatever' }");
        ScreenLayout actual = testObject.getLayout(context);
        Label label = (Label) actual.components[0];
        assertEquals("Whatever",label.getText());
    }

    @Test
    public void getLayout_uses_context_to_return_portrait_layout_when_portrait() {
//        hashSourceCode = lines(
//            "layout { ?(portrait) ^portrait : landscape }",
//            "portrait { ^'Family'}"
//        );
        hashSourceCode = lines(
            "layout { ^ ? (portrait) layout_portrait : layout_landscape }",
            "layout_portrait { ^ 'Family' }"
        );
        context.put("portrait", true);
        ScreenLayout actual = testObject.getLayout(context);
        Label label = (Label) actual.components[0];
        assertEquals("Family",label.getText());
    }

    private String lines(String...lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line + " \r\n");
        }
        return out.toString().replaceAll("'", "\"");
    }

}
