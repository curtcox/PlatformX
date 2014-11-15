package oc1.screen;

import com.codename1.ui.Button;
import hash.NamedExpression;
import java.util.HashMap;

/**
 * A map of component methods for adding to a ScreenContext.
 * @author Curt
 */
final class DynamicScreenComponentMethods
    extends HashMap
{
    DynamicScreenComponentMethods() {
        put(button());        
        put(link());        
    }   
    
    private void put(NamedExpression expression) {
        put(expression.name,expression);
    }
    
    private NamedExpression button() {
        return new NamedExpression("button") {
            
            @Override
            public Object invoke(Object[] values) {
                String text = string(values[0]);
                String image = string(values[1]);
                String leadingTo = string(values[2]);
                return button(text,image,leadingTo);
            }

        };
    }

    private NamedExpression link() {
        return new NamedExpression("link") {
            
            @Override
            public Object invoke(Object[] values) {
                String text = string(values[0]);
                return link(text);
            }

        };
    }

    private String string(Object value) {
        return (String) value;
    }

    private Button button(String text, String image, String leadingTo) {
        return ScreenButton.textAndImageLeadingTo(text,image,leadingTo);
    }

    private Button link(String text) {
        return ScreenButton.textAndLeadingTo(text, text);
    }
    
}
