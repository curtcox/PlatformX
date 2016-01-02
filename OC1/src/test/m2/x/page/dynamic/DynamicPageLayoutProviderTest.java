package x.page.dynamic;

import config.ShouldRun;
import fake.FakeXRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.event.StringSource;
import x.page.PageLink;
import x.uiwidget.XContainer;
import x.uiwidget.XGrid;
import x.uiwidget.XLabel;
import x.util.Strings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

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
        assumeTrue(ShouldRun.X);
        FakeXRegistryLoader.load();
    }

    @Test
    public void getLayout_returns_layout_with_message_when_source_is_null() {
        sourceCode = null;
        XLabel label = (XLabel) testObject.getLayout(link,context);
        assertSpanLabelTextContains(label,"Source is not valid Hash");
    }

    @Test
    public void getLayout_returns_layout_with_message_when_source_is_empty() {
        sourceCode = "";
        XLabel label = (XLabel) testObject.getLayout(link,context);
        assertTrue(Strings.contains(label.toString(),""));
    }

    @Test
    public void getLayout_returns_layout_with_message_when_layout_not_defined() {
        sourceCode = "layover {}";
        XContainer actual = (XContainer) testObject.getLayout(link,context);
        assertSpanLabelTextContains(actual.components[0],"RuntimeException");
        assertSpanLabelTextContains(actual.components[1],"layout not found");
    }

    private void assertSpanLabelTextContains(Object component, String target) {
        XLabel label = (XLabel) component;
        String string = label.text;
        assertTrue(string,Strings.contains(string,target));
    }
    
    @Test
    public void getLayout_returns_layout_with_label_when_layout_returns_a_string() {
        sourceCode = lines("layout { 'Whatever' }");
        XLabel label = (XLabel) testObject.getLayout(link,context);
        assertEquals("Whatever",label.text);
    }

    @Test
    public void getLayout_returns_grid_with_components_when_layout_specifies_a_grid() {
        context.putAll(new DynamicScreenLayoutMethods());
        
        sourceCode = lines("layout { grid(1 2 'one' 'two') }");

        XContainer actual = (XContainer) testObject.getLayout(link,context);
        assertTrue(actual instanceof XGrid);
        XGrid layout = (XGrid) actual;
        assertEquals(1,layout.rows);
        assertEquals(2,layout.columns);
        XLabel label1 = (XLabel) actual.components[0];
        XLabel label2 = (XLabel) actual.components[1];
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
        XLabel label = (XLabel) testObject.getLayout(link,context);
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
