package x.page.dynamic;

import hash.NamedExpression;
import x.Registry;
import x.util.XReflection;

import java.util.HashMap;

/**
 * A map of methods for dynamically invoking ScreenContext methods.
 */
final class DynamicScreenMethodInvocations
    extends HashMap
{
    DynamicScreenMethodInvocations() {
        put("set",set());        
    }   
    
    private NamedExpression set() {
        return new NamedExpression("set") {
            @Override
            public Object invoke(Object[] values) {
                Object object = values[0];
                String field = (String) values[1];
                Object value = values[2];
                set(object,field,value);
                return object;
            }
        };
    }
    
    private void set(Object object, String field, Object value) {
        reflection().set(object,field,value);
    }

    XReflection reflection() {
        return Registry.get(XReflection.class);
    }
}
