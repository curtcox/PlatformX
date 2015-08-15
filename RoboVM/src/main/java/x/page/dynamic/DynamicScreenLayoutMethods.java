package x.page.dynamic;

import hash.NamedExpression;
import x.uiwidget.*;

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

    private XComponent[] components(Object[] values, int offset) {
        XComponent[] components = new XComponent[values.length-offset];
        for (int i=0; i<components.length; i++) {
            components[i] = component(values[i+offset]);
        }
        return components;
    }

    private XComponent component(Object o) {
        if (o == null)                 { return new XLabel(""); }
        if (o instanceof XComponent)  { return (XComponent) o; }
        return new XLabel(o.toString());
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
    
    private XComponent grid(int rows,int columns,XComponent[] components) {
        return new XGrid(rows,columns,components);
    }

    private XComponent table(int rows,int columns,XComponent[] components) {
        return new XTable(rows,columns,components);
    }

    private XComponent flow(XComponent[] components) {
        return new XFlow(components);
    }

    private XComponent column(XComponent[] components) {
        return new XColumn(components);
    }

    private XComponent row(XComponent[] components) {
        return new XRow(components);
    }

}
