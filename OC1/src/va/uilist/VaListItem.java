package va.uilist;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import x.uilist.IXListCell;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

final class VaListItem
    implements Item
{
    final List propertyIds = Arrays.asList("first", "second", "icon");
    final IXListCell.Config config;

    VaListItem(IXListCell.Config config) {
        this.config = config;
    }

    @Override
    public Property getItemProperty(Object id) {
        if (id.equals("first")) {
            return new VaReadOnlyProperty(config.first);
        }
        if (id.equals("second")) {
            return new VaReadOnlyProperty(config.second);
        }
        if (id.equals("icon")) {
            return new VaReadOnlyProperty(config.icon);
        }
        return null;
    }

    @Override
    public Collection<?> getItemPropertyIds() {
        return propertyIds;
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
