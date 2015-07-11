package va.uilist;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import x.uilist.IXListCell;
import x.uiwidget.XImage;

import java.net.URI;
import java.util.Iterator;

/**
 * A compound list cell.
 */
final class VaListCell
    extends AbstractLayout
    implements IXListCell
{
    final Label firstRow;
    final Label secondRow;

    public VaListCell() {
        firstRow = new Label();
        secondRow = new Label();
    }

    @Override
    public void setFirstRowText(String text) {
        firstRow.setValue(text);
    }

    @Override
    public void setSecondRowText(String text) {
        secondRow.setValue(text);
    }

    @Override
    public void setIcon(XImage icon) {

    }

    @Override
    public void setIcon(URI uri) {

    }

    @Override
    public void replaceComponent(Component oldComponent, Component newComponent) {

    }

    @Override
    public int getComponentCount() {
        return 0;
    }

    @Override
    public Iterator<Component> iterator() {
        return null;
    }
}
