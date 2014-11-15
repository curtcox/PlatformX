package oc1.screen;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import fake.FakeRegistryLoader;
import oc1.event.StringSource;
import oc1.util.Strings;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DynamicScreenLayoutProviderTest {

    String sourceCode;
    ScreenContext context = new ScreenContext();
    StringSource source = new StringSource() {
        public String getString() {
            return sourceCode;
        }
    };
    DynamicScreenLayoutProvider testObject = new DynamicScreenLayoutProvider(source);
    
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
        sourceCode = lines("layout { 'Whatever' }");
        ScreenLayout actual = testObject.getLayout(context);
        Label label = (Label) actual.components[0];
        assertEquals("Whatever",label.getText());
    }

    @Test
    public void getLayout_returns_grid_with_components_when_layout_specifies_a_grid() {
        context.putAll(new DynamicScreenLayoutMethods());
        
        sourceCode = lines("layout { grid(1 2 'one' 'two') }");
        
        ScreenLayout actual = testObject.getLayout(context);
        assertTrue(actual.layout instanceof GridLayout);
        GridLayout layout = (GridLayout) actual.layout;
        assertEquals(1,layout.getRows());
        assertEquals(2,layout.getColumns());
        Label label1 = (Label) actual.components[0];
        Label label2 = (Label) actual.components[1];
        assertEquals("one",label1.getText());
        assertEquals("two",label2.getText());
    }

    @Test
    public void getLayout_uses_context_to_return_portrait_layout_when_portrait() {
        sourceCode = lines(
            "layout { (portrait) ? layout_portrait : layout_landscape }",
            "layout_portrait { 'Family' }"
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
