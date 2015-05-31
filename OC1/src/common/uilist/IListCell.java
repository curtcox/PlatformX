package common.uilist;

import common.uiwidget.UIImage;

import java.net.URI;

public interface IListCell {
    void setFirstRowText(String text);
    void setSecondRowText(String text);
    void setIcon(UIImage icon);
    void setIcon(URI uri);
}
