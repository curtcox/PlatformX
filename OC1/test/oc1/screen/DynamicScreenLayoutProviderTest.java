package oc1.screen;

import common.screen.ScreenLayout;
import common.ui.UIGridLayout;
import common.ui.UILabel;
import fake.FakeRegistryLoader;
import common.event.StringSource;
import oc1.util.Strings;
import org.junit.*;
import static org.junit.Assert.*;

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
        sourceCode = null;
        ScreenLayout actual = testObject.getLayout(context);
        Object component = actual.components[0];
        assertSpanLabelTextContains(component,"Source is not valid Hash");
    }

    @Test
    public void getLayout_returns_layout_with_message_when_source_is_empty() {
        sourceCode = "";
        ScreenLayout actual = testObject.getLayout(context);
        assertTrue(Strings.contains(actual.components[0].toString(),""));
    }

    @Test
    public void getLayout_returns_layout_with_message_when_layout_not_defined() {
        sourceCode = "layover {}";
        ScreenLayout actual = testObject.getLayout(context);
        assertSpanLabelTextContains(actual.components[0],"RuntimeException");
        assertSpanLabelTextContains(actual.components[1],"layout not found");
    }

    private void assertSpanLabelTextContains(Object component, String target) {
        UILabel label = (UILabel) component;
        String string = label.text;
        assertTrue(string,Strings.contains(string,target));
    }
    
    @Test
    public void getLayout_returns_layout_with_label_when_layout_returns_a_string() {
        sourceCode = lines("layout { 'Whatever' }");
        ScreenLayout actual = testObject.getLayout(context);
        UILabel label = (UILabel) actual.components[0];
        assertEquals("Whatever",label.text);
    }

    @Test
    public void getLayout_returns_grid_with_components_when_layout_specifies_a_grid() {
        context.putAll(new DynamicScreenLayoutMethods());
        
        sourceCode = lines("layout { grid(1 2 'one' 'two') }");
        
        ScreenLayout actual = testObject.getLayout(context);
        assertTrue(actual.layout instanceof UIGridLayout);
        UIGridLayout layout = (UIGridLayout) actual.layout;
        assertEquals(1,layout.rows);
        assertEquals(2,layout.columns);
        UILabel label1 = (UILabel) actual.components[0];
        UILabel label2 = (UILabel) actual.components[1];
        assertEquals("one",label1.text);
        assertEquals("two",label2.text);
    }

    @Test
    public void getLayout_uses_context_to_return_portrait_layout_when_portrait() {
        sourceCode = lines(
            "layout { (portrait) ? layout_portrait : layout_landscape }",
            "layout_portrait { 'Family' }"
        );
        context.put("portrait", true);
        ScreenLayout actual = testObject.getLayout(context);
        UILabel label = (UILabel) actual.components[0];
        assertEquals("Family",label.text);
    }

    private String lines(String...lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line + " \r\n");
        }
        return out.toString().replaceAll("'", "\"");
    }

}
