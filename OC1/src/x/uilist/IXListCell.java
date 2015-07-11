package x.uilist;

import x.uiwidget.XImage;

import java.net.URI;

public interface IXListCell {
    void setFirstRowText(String text);
    void setSecondRowText(String text);
    void setIcon(XImage icon);
    void setIcon(URI uri);
}
