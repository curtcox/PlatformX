package x.uiwidget;

import java.util.Arrays;

public class XContainer
    extends XComponent
{

    public final XComponent[] components;

    XContainer(XComponent[] components) {
        this.components = components;
    }

    public String toString() {
        return Arrays.asList(components).toString();
    }
}
