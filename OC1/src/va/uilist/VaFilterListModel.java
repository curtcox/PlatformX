package va.uilist;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.UnsupportedFilterException;
import x.event.LiveList;
import x.uilist.ListFilter;

import java.util.Collection;

final class VaFilterListModel<T>
    implements Container.Filterable {
    private final LiveList filtered;
    private ListFilter filter = ListFilter.ALLOW_ALL;

    private VaFilterListModel(LiveList filtered) {
        this.filtered = filtered;
    }

    public static VaFilterListModel of(LiveList filtered) {
        VaFilterListModel model = new VaFilterListModel(filtered);
        return model;
    }

    public void setFilter(ListFilter filter) {
        this.filter = filter;
    }


    @Override
    public void addContainerFilter(Filter filter) throws UnsupportedFilterException {

    }

    @Override
    public void removeContainerFilter(Filter filter) {

    }

    @Override
    public void removeAllContainerFilters() {

    }

    @Override
    public Collection<Filter> getContainerFilters() {
        return null;
    }

    @Override
    public Item getItem(Object itemId) {
        return null;
    }

    @Override
    public Collection<?> getContainerPropertyIds() {
        return null;
    }

    @Override
    public Collection<?> getItemIds() {
        return null;
    }

    @Override
    public Property getContainerProperty(Object itemId, Object propertyId) {
        return null;
    }

    @Override
    public Class<?> getType(Object propertyId) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean containsId(Object itemId) {
        return false;
    }

    @Override
    public Item addItem(Object itemId) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public Object addItem() throws UnsupportedOperationException {
        return null;
    }

    @Override
    public boolean removeItem(Object itemId) throws UnsupportedOperationException {
        return false;
    }

    @Override
    public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue) throws UnsupportedOperationException {
        return false;
    }

    @Override
    public boolean removeContainerProperty(Object propertyId) throws UnsupportedOperationException {
        return false;
    }

    @Override
    public boolean removeAllItems() throws UnsupportedOperationException {
        return false;
    }

    public void dataChanged() {

    }
}