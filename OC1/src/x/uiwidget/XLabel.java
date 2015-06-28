package x.uiwidget;

import java.net.URI;

public class XLabel
    extends XComponent
{
    public String text = "";
    public URI icon;

    public XLabel() {}

    public XLabel(String text) {
        this.text = text;
    }
}
