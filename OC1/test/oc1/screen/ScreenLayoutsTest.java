package oc1.screen;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import fake.FakeRegistryLoader;
import hash.NamedExpression;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ScreenLayoutsTest {
    
    ScreenLayouts testObject = new ScreenLayouts();
    
    @Before
    public void setUp() {
        FakeRegistryLoader.load();
    }

    @Test
    public void get_grid() {
        Object value = testObject.get("grid");
        
        NamedExpression expression = (NamedExpression) value;
        ScreenLayout screen = (ScreenLayout) expression.invoke(new Object[] {4,2,"red","green"});
        GridLayout grid = (GridLayout) screen.layout;
        assertEquals(4,grid.getRows());
        assertEquals(2,grid.getColumns());
        Label red = (Label) screen.components[0];
        Label green = (Label) screen.components[1];
        assertEquals("red",red.getText());
        assertEquals("green",green.getText());
    }
    
}
