package common.page.dynamic;

import common.screenparts.ScreenButton;
import common.uiwidget.UILabel;
import hash.NamedExpression;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class DynamicPageComponentMethodsTest {

    DynamicScreenComponentMethods testObject = new DynamicScreenComponentMethods();

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void has_expected_keys() {
        assertTrue(testObject.containsKey("button"));
        assertTrue(testObject.containsKey("image"));
        assertTrue(testObject.containsKey("link"));
    }

    @Test
    public void invoking_button_creates_button() {
        String text = "text";
        String image = "image";
        String leadingTo = "leading to";
        ScreenButton button = (ScreenButton) invoke("button",text,image,leadingTo);
        assertSame(text,button.text);
        assertSame(image,button.icon);
        assertSame(leadingTo,button.link.tags.toString());
    }

    @Test
    public void invoking_image_creates_label() throws URISyntaxException {
        URI uri = new URI("image_uri");
        UILabel label = (UILabel) invoke("image",uri);
        assertEquals(uri, label.icon);
    }

    @Test
    public void invoking_link_creates_button_leading_to_value_with_text_value() {
        String text = "text";
        ScreenButton button = (ScreenButton) invoke("link",text);
        assertSame(text,button.text);
        assertSame(text, button.link.tags.toString());
    }

    Object invoke(String name, Object... args) {
        return expression(name).invoke(args);
    }

    NamedExpression expression(String name) {
        return (NamedExpression) testObject.get(name);
    }
}