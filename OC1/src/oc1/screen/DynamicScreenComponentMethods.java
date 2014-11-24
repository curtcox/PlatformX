package oc1.screen;

import com.codename1.components.*;
import com.codename1.ui.*;
import hash.NamedExpression;
import java.net.*;
import java.util.*;
import oc1.app.Registry;
import oc1.ui.Icons;

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
                SpanLabel button = new SpanLabel();
                button.setIcon(image(uri));
                return button;
            }

        };
    }

    Image image(URI uri) {
        Display display = Registry.get(Display.class);
        Form form = display.getCurrent();
        return Icons.of().getImage(uri,form.getWidth(),form.getHeight());
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

    private Button button(String text, String image, String leadingTo) {
        return ScreenButton.textAndImageLeadingTo(text,image,leadingTo);
    }

    private Button link(String text) {
        return ScreenButton.textAndLeadingTo(text, text);
    }
    
}
