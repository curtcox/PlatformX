package common.screen.dynamic;

import common.ui.UILabel;
import hash.NamedExpression;
import java.net.*;
import java.util.*;

import c1.screenparts.ScreenButton;
import c1.ui.ActionButton;

/**
 * A map of component methods for adding to a ScreenContext.
 * @author Curt
 */
final class DynamicScreenComponentMethods
    extends HashMap
{
    DynamicScreenComponentMethods() {
        put(image());        
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

    private NamedExpression image() {
        return new NamedExpression("image") {
            
            @Override
            public Object invoke(Object[] values) {
                URI uri = uri(values[0]);
                UILabel button = new UILabel();
                button.icon = uri;
                return button;
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

    private URI uri(Object value) {
        try {
            return new URI(string(value));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String string(Object value) {
        return (value==null) ? "" : value.toString();
    }

    private ActionButton button(String text, String image, String leadingTo) {
        return ScreenButton.textAndImageLeadingTo(text, image, leadingTo);
    }

    private ActionButton link(String text) {
        return ScreenButton.textAndLeadingTo(text, text);
    }
    
}
