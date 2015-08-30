package va.uilist;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import x.uilist.IXListCell;

import java.util.Collection;

final class VaListItem
    implements Item
{
    final IXListCell.Config config;

    VaListItem(IXListCell.Config config) {
        this.config = config;
    }

    @Override
    public Property getItemProperty(Object id) {
        return null;
    }

    @Override
    public Collection<?> getItemPropertyIds() {
        return null;
    }

    @Override
    public boolean addItemProperty(Object id, Property property) {
        throw never();
    }

    @Override
    public boolean removeItemProperty(Object id) {
        throw never();
    }

    private UnsupportedOperationException never() {
        return new UnsupportedOperationException();
    }
}
