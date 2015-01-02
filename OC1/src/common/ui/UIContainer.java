package common.ui;

import java.util.Arrays;

public class UIContainer
    extends UIComponent
{

    public final UIComponent[] components;

    UIContainer(UIComponent[] components) {
        this.components = components;
    }

    public String toString() {
        return Arrays.asList(components).toString();
    }
}
