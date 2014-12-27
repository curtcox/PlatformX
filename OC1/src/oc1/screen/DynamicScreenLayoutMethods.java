package oc1.screen;

import common.screen.ScreenLayout;
import common.ui.*;
import hash.NamedExpression;
import java.util.HashMap;

/**
 * A map of layout methods for adding to a ScreenContext.
 * @author Curt
 */
final class DynamicScreenLayoutMethods
    extends HashMap
{
    private final ComponentGenerator renderer = new ComponentGenerator();

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
                return grid(rows, columns, components(values, 2));
            }
        };
    }

    private NamedExpression table() {
        return new NamedExpression("table") {
            @Override
            public Object invoke(Object[] values) {
                int rows    = integer(values[0]);
                int columns = integer(values[1]);
                return table(rows, columns, components(values, 2));
            }
        };
    }

    private NamedExpression flow() {
        return new NamedExpression("flow") {
            @Override
            public Object invoke(Object[] values) {
                return flow(components(values, 0));
            }
        };
    }

    private NamedExpression row() {
        return new NamedExpression("row") {
            @Override
            public Object invoke(Object[] values) {
                return row(components(values, 0));
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

    private UIComponent[] components(Object[] values, int offset) {
        UIComponent[] components = new UIComponent[values.length-offset];
        for (int i=0; i<components.length; i++) {
            components[i] = component(values[i+offset]);
        }
        return components;
    }

    private UIComponent component(Object o) {
        if (o == null)                 { return new UILabel(""); }
        if (o instanceof UIComponent)  { return (UIComponent) o; }
        if (o instanceof ScreenLayout) { return renderer.toComponent((ScreenLayout) o); }
        return new UILabel(o.toString());
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
    
    private ScreenLayout grid(int rows,int columns,UIComponent[] components) {
        return screen(new UIGridLayout(rows,columns,components));
    }

    private ScreenLayout table(int rows,int columns,UIComponent[] components) {
        return screen(new UITableLayout(rows,columns,components));
    }

    private ScreenLayout flow(UIComponent[] components) {
        return screen(new UIFlowLayout(components));
    }

    private ScreenLayout column(UIComponent[] components) {
        return screen(new UIColumnLayout(components));
    }

    private ScreenLayout row(UIComponent[] components) {
        return screen(new UIRowLayout(components));
    }

    private ScreenLayout screen(UILayout layout, UIComponent... components) {
        return new ScreenLayout(layout,components);
    }
}
