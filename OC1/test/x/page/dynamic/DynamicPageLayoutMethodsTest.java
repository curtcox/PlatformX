package x.page.dynamic;

import config.ShouldRun;
import fake.FakeXRegistryLoader;
import hash.NamedExpression;
import org.junit.Before;
import org.junit.Test;
import x.uiwidget.XContainer;
import x.uiwidget.XGrid;
import x.uiwidget.XLabel;
import x.uiwidget.XTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

public class DynamicPageLayoutMethodsTest {
    
    DynamicScreenLayoutMethods testObject = new DynamicScreenLayoutMethods();
    
    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
        FakeXRegistryLoader.load();
    }

    @Test
    public void get_grid() {
        Object value = testObject.get("grid");
        
        NamedExpression expression = (NamedExpression) value;
        XContainer screen = (XContainer) expression.invoke(new Object[] {4,2,"red","green"});
        XGrid grid = (XGrid) screen;
        assertEquals(4,grid.rows);
        assertEquals(2,grid.columns);
        XLabel red = (XLabel) screen.components[0];
        XLabel green = (XLabel) screen.components[1];
        assertEquals("red",red.text);
        assertEquals("green",green.text);
    }

    @Test
    public void get_table() {
        Object value = testObject.get("table");
        
        NamedExpression expression = (NamedExpression) value;
        XTable table = (XTable) expression.invoke(new Object[] {4,2,"red","green"});
        assertEquals(4,table.rows);
        assertEquals(2,table.columns);
        XLabel red = (XLabel) table.components[0];
        XLabel green = (XLabel) table.components[1];
        assertEquals("red",red.text);
        assertEquals("green",green.text);
    }

}
