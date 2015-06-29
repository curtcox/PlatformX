package x.uilist;

import x.event.LiveList;

public final class XListOffsets<T> {

    private final LiveList<T> filtered;
    private ListFilter filter = ListFilter.ALLOW_ALL;

    private XListOffsets(LiveList<T> filtered) {
        this.filtered = filtered;
    }

    public static XListOffsets of(LiveList filtered) {
        XListOffsets offsets = new XListOffsets(filtered);
        offsets.calculate();
        return offsets;
    }

    public int getSize() {
        int passed = 0;
        for (int i=0; i<filtered.size(); i++) {
            if (filter.passes(filtered.get(i))) {
                passed++;
            }
        }
        return passed;
    }

    public T getElementAt(int index) {
        int passed = -1;
        for (int i=0; i<filtered.size(); i++) {
            T item = filtered.get(i);
            if (filter.passes(item)) {
                passed++;
            }
            if (passed==index) {
                return item;
            }
        }
        return null;
    }

    public void setFilter(ListFilter filter) {
        this.filter = filter;
    }

    public void calculate() {

    }
}
