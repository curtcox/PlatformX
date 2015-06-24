package x.page.dynamic;

import x.uiwidget.UIGrid;
import x.uiwidget.UILabel;
import x.uiwidget.UIContainer;
import x.uiwidget.UITable;
import fake.FakeC1RegistryLoader;
import hash.NamedExpression;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DynamicPageLayoutMethodsTest {
    
    DynamicScreenLayoutMethods testObject = new DynamicScreenLayoutMethods();
    
    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
    }

    @Test
    public void get_grid() {
        Object value = testObject.get("grid");
        
        NamedExpression expression = (NamedExpression) value;
        UIContainer screen = (UIContainer) expression.invoke(new Object[] {4,2,"red","green"});
        UIGrid grid = (UIGrid) screen;
        assertEquals(4,grid.rows);
        assertEquals(2,grid.columns);
        UILabel red = (UILabel) screen.components[0];
        UILabel green = (UILabel) screen.components[1];
        assertEquals("red",red.text);
        assertEquals("green",green.text);
    }

    @Test
    public void get_table() {
        Object value = testObject.get("table");
        
        NamedExpression expression = (NamedExpression) value;
        UITable table = (UITable) expression.invoke(new Object[] {4,2,"red","green"});
        assertEquals(4,table.rows);
        assertEquals(2,table.columns);
        UILabel red = (UILabel) table.components[0];
        UILabel green = (UILabel) table.components[1];
        assertEquals("red",red.text);
        assertEquals("green",green.text);
    }

}
