package common.screen.dynamic;

import hash.NamedExpression;
import java.util.HashMap;
import common.util.Reflection;

/**
 * A map of methods for dynamically invoking ScreenContext methods.
 * @author Curt
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
        Reflection.set(object,field,value);
    }

}
