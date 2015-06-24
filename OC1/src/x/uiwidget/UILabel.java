package x.uiwidget;

import java.net.URI;

public class UILabel
    extends UIComponent
{
    public String text = "";
    public URI icon;

    public UILabel() {}

    public UILabel(String text) {
        this.text = text;
    }
}
