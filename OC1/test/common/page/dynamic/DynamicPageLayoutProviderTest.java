package common.page.dynamic;

import common.page.PageLink;
import common.uiwidget.UIGrid;
import common.uiwidget.UILabel;
import common.uiwidget.UIContainer;
import fake.FakeC1RegistryLoader;
import common.event.StringSource;
import common.util.Strings;
import org.junit.*;
import static org.junit.Assert.*;

public class DynamicPageLayoutProviderTest {

    String sourceCode;
    PageLink link;
    ScreenContext context = new ScreenContext();
    StringSource source = new StringSource() {
        public String getString() {
            return sourceCode;
        }
    };
    DynamicScreenLayoutProvider testObject = new DynamicScreenLayoutProvider(source);
    
    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
    }

    @Test
    public void getLayout_returns_layout_with_message_when_source_is_null() {
        sourceCode = null;
        UILabel label = (UILabel) testObject.getLayout(link,context);
        assertSpanLabelTextContains(label,"Source is not valid Hash");
    }

    @Test
    public void getLayout_returns_layout_with_message_when_source_is_empty() {
        sourceCode = "";
        UILabel label = (UILabel) testObject.getLayout(link,context);
        assertTrue(Strings.contains(label.toString(),""));
    }

    @Test
    public void getLayout_returns_layout_with_message_when_layout_not_defined() {
        sourceCode = "layover {}";
        UIContainer actual = (UIContainer) testObject.getLayout(link,context);
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
        UILabel label = (UILabel) testObject.getLayout(link,context);
        assertEquals("Whatever",label.text);
    }

    @Test
    public void getLayout_returns_grid_with_components_when_layout_specifies_a_grid() {
        context.putAll(new DynamicScreenLayoutMethods());
        
        sourceCode = lines("layout { grid(1 2 'one' 'two') }");

        UIContainer actual = (UIContainer) testObject.getLayout(link,context);
        assertTrue(actual instanceof UIGrid);
        UIGrid layout = (UIGrid) actual;
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
        UILabel label = (UILabel) testObject.getLayout(link,context);
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
