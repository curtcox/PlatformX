package va.uilist;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import x.event.Change;
import x.event.LiveList;
import x.uilist.IXListCell;
import x.uilist.ListFilter;
import x.uilist.XListOffsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

final class VaFilterListModel<T>
    implements Container.Filterable
{
    private final XListOffsets<T> offsets;
    private final List<Integer> ids = new ArrayList();
    private final List propertyIds = Arrays.asList("first","second","icon");
    private final IXListCell.ConfigProducer producer;

    private VaFilterListModel(LiveList filtered, IXListCell.ConfigProducer producer) {
        this.producer = producer;
        this.offsets = XListOffsets.of(filtered);
    }

    public static VaFilterListModel of(LiveList filtered, IXListCell.ConfigProducer producer) {
        VaFilterListModel model = new VaFilterListModel(filtered,producer);
        model.setIds();
        model.listenTo(filtered);
        return model;
    }

    private void listenTo(LiveList filtered) {
        filtered.addListener(new Change.Listener() {
            @Override
            public void onChange() {
                dataChanged();
            }
        });
    }

    public void setFilter(ListFilter filter) {
        offsets.setFilter(filter);
        dataChanged();
    }

    private void setIds() {
        ids.clear();
        for (int i=0; i<offsets.getSize(); i++) {
            ids.add(i);
        }
    }

    @Override
    public Item getItem(Object itemId) {
        int index = (Integer) itemId;
        return new VaListItem(producer.configFor(value(index)));
    }

    private Object value(int index) {
        return offsets.getElementAt(index);
    }

    @Override
    public Collection<?> getContainerPropertyIds() {
        return propertyIds;
    }

    @Override
    public int size() {
        return offsets.getSize();
    }

    public void dataChanged() {
        offsets.calculate();
        setIds();
    }

    private UnsupportedOperationException never() {
        return new UnsupportedOperationException();
    }

    @Override
    public Collection<?> getItemIds() {
        return ids;
    }

    @Override
    public Property getContainerProperty(Object itemId, Object propertyId) {
        return getItem(itemId).getItemProperty(propertyId);
    }

    @Override
    public Class<?> getType(Object propertyId) {
        return String.class;
    }

    @Override
    public boolean containsId(Object itemId) {
        throw never();
    }

    @Override
    public Item addItem(Object itemId) {
        throw never();
    }

    @Override
    public Object addItem() {
        throw never();
    }

    @Override
    public boolean removeItem(Object itemId) {
        throw never();
    }

    @Override
    public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue) {
        throw never();
    }

    @Override
    public boolean removeContainerProperty(Object propertyId) {
        throw never();
    }

    @Override
    public boolean removeAllItems() {
        throw never();
    }

    @Override
    public void addContainerFilter(Filter filter) {
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

}