package va.uilist;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.UnsupportedFilterException;
import x.event.LiveList;
import x.uilist.ListFilter;
import x.uilist.XListOffsets;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

final class VaFilterListModel<T>
    implements Container.Filterable
{
    private final LiveList filtered;
    private ListFilter filter = ListFilter.ALLOW_ALL;
    private final XListOffsets<T> offsets;
    private final List propertyIds = Arrays.asList("first","second","icon");

    private VaFilterListModel(LiveList filtered) {
        this.filtered = filtered;
        this.offsets = XListOffsets.of(filtered);
    }

    public static VaFilterListModel of(LiveList filtered) {
        VaFilterListModel model = new VaFilterListModel(filtered);
        return model;
    }

    public void setFilter(ListFilter filter) {
        this.filter = filter;
        dataChanged();
    }

    @Override
    public void addContainerFilter(Filter filter) throws UnsupportedFilterException {
        throw never();
    }

    @Override
    public void removeContainerFilter(Filter filter) {
        throw never();
    }

    @Override
    public void removeAllContainerFilters() {
        throw never();
    }

    @Override
    public Collection<Filter> getContainerFilters() {
        throw never();
    }

    @Override
    public Item getItem(Object itemId) {
        return (Item) offsets.getElementAt((Integer) itemId);
    }

    @Override
    public Collection<?> getContainerPropertyIds() {
        return propertyIds;
    }

    @Override
    public Collection<?> getItemIds() {
        throw never();
    }

    @Override
    public Property getContainerProperty(Object itemId, Object propertyId) {
        throw never();
    }

    @Override
    public Class<?> getType(Object propertyId) {
        throw never();
    }

    @Override
    public int size() {
        return offsets.getSize();
    }

    @Override
    public boolean containsId(Object itemId) {
        throw never();
    }

    @Override
    public Item addItem(Object itemId) throws UnsupportedOperationException {
        throw never();
    }

    @Override
    public Object addItem() throws UnsupportedOperationException {
        throw never();
    }

    @Override
    public boolean removeItem(Object itemId) throws UnsupportedOperationException {
        throw never();
    }

    @Override
    public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue) throws UnsupportedOperationException {
        throw never();
    }

    @Override
    public boolean removeContainerProperty(Object propertyId) throws UnsupportedOperationException {
        throw never();
    }

    @Override
    public boolean removeAllItems() throws UnsupportedOperationException {
        throw never();
    }

    public void dataChanged() {
        offsets.calculate();
    }

    private RuntimeException never() {
        return new RuntimeException();
    }
}