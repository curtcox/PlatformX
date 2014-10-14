package oc1.screen;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import fake.FakeRegistryLoader;
import oc1.event.StringSource;
import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DynamicScreenLayoutTest {

    String sourceCode;
    ScreenContext context = new ScreenContext();
    StringSource source = new StringSource() {
        public String getString() {
            return sourceCode;
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
        sourceCode = "";
        ScreenLayout actual = testObject.getLayout(context);
        assertTrue(Strings.contains(actual.components[0].toString(),"Source is not valid Hash"));
    }

    @Test
    public void getLayout_returns_layout_with_message_when_layout_not_defined() {
        sourceCode = "layover {}";
        ScreenLayout actual = testObject.getLayout(context);
        String string = actual.components[0].toString();
        assertTrue(string,Strings.contains(string,"layout not found"));
    }

    @Test
    public void getLayout_returns_layout_with_label_when_layout_returns_a_string() {
        sourceCode = lines("layout { ^'Whatever' }");
        ScreenLayout actual = testObject.getLayout(context);
        Label label = (Label) actual.components[0];
        assertEquals("Whatever",label.getText());
    }

    @Test
    public void getLayout_returns_grid_with_components_when_layout_specifies_a_grid() {
        sourceCode = lines("layout { ^grid(1 2 'one' 'two') }");
        ScreenLayout actual = testObject.getLayout(context);
        assertEquals(new GridLayout(1,2),actual.layout);
        Label label1 = (Label) actual.components[0];
        Label label2 = (Label) actual.components[1];
        assertEquals("one",label1.getText());
        assertEquals("two",label2.getText());
    }

    @Test
    public void getLayout_uses_context_to_return_portrait_layout_when_portrait() {
        sourceCode = lines(
            "layout { ^ (portrait) ? layout_portrait : layout_landscape }",
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
