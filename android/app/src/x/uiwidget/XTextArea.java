package x.uiwidget;

public final class XTextArea
    extends XComponent
{
    public String text = "";
    public boolean editable;

    public XTextArea() {}

    public XTextArea(String text) {
        this.text = text;
    }
}
