package x.uilist;

import x.uiwidget.XImage;

import java.net.URI;

public interface IListCell {
    void setFirstRowText(String text);
    void setSecondRowText(String text);
    void setIcon(XImage icon);
    void setIcon(URI uri);
}
