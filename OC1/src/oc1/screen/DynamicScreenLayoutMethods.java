package oc1.screen;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.table.TableLayout;
import hash.NamedExpression;
import java.util.HashMap;

/**
 * A map of layout methods for adding to a ScreenContext.
 * @author Curt
 */
final class DynamicScreenLayoutMethods
    extends HashMap
{
    DynamicScreenLayoutMethods() {
        put("grid",grid());        
        put("table",table());        
        put("row",row());        
        put("column",column());        
        put("flow",flow());        
    }   
    
    private NamedExpression grid() {
        return new NamedExpression("grid") {
            @Override
            public Object invoke(Object[] values) {
                int rows    = integer(values[0]);
                int columns = integer(values[1]);
                return grid(rows,columns,components(values,2));
            }
        };
    }

    private NamedExpression table() {
        return new NamedExpression("table") {
            @Override
            public Object invoke(Object[] values) {
                int rows    = integer(values[0]);
                int columns = integer(values[1]);
                return table(rows,columns,components(values,2));
            }
        };
    }

    private NamedExpression flow() {
        return new NamedExpression("flow") {
            @Override
            public Object invoke(Object[] values) {
                return flow(components(values,0));
            }
        };
    }

    private NamedExpression row() {
        return new NamedExpression("row") {
            @Override
            public Object invoke(Object[] values) {
                return row(components(values,0));
            }
        };
    }

    private NamedExpression column() {
        return new NamedExpression("column") {
            @Override
            public Object invoke(Object[] values) {
                return column(components(values,0));
            }
        };
    }

    private Component[] components(Object[] values, int offset) {
        Component[] components = new Component[values.length-offset];
        for (int i=0; i<components.length; i++) {
            components[i] = component(values[i+offset]);
        }
        return components;
    }

    private Component component(Object o) {
        if (o == null)                 { return new Label(""); }
        if (o instanceof Component)    { return (Component) o; }    
        if (o instanceof ScreenLayout) { return ((ScreenLayout)o).toComponent(); }
        return new Label(o.toString());
    }
    
    private int integer(Object o) {
        if (o instanceof Long) {
            long value = (Long) o;
            return (int) value;
        }
        if (o instanceof Integer) {
            return (Integer)o;
        }
        return 0;
    }
    
    private ScreenLayout grid(int rows,int columns,Component[] components) {
        return screen(new GridLayout(rows,columns),components);
    }

    private ScreenLayout table(int rows,int columns,Component[] components) {
        return screen(new TableLayout(rows,columns),components);
    }

    private ScreenLayout flow(Component[] components) {
        return screen(new FlowLayout(),components);
    }

    private ScreenLayout column(Component[] components) {
        return screen(new BoxLayout(BoxLayout.Y_AXIS),components);
    }

    private ScreenLayout row(Component[] components) {
        return screen(new BoxLayout(BoxLayout.X_AXIS),components);
    }

    private ScreenLayout screen(Layout layout, Component... components) {
        return new ScreenLayout(layout,components);
    }
}
