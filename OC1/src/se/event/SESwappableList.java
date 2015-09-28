package se.event;

import x.event.Change;
import x.event.SwappableList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class SESwappableList
    implements SwappableList
{

    private List list;
    private Change.Listener listener;

    @Override
    public int size() {
        return list==null ? 0 : list.size();
    }

    @Override
    public Object get(int index) {
        return list.get(index);
    }

    @Override
    public void become(List list) {
        this.list = list;
        if (listener!=null) {
            listener.onChange();
        }
    }

    @Override
    public void addListener(Change.Listener listener) {
        this.listener = listener;
    }

    public boolean                       isEmpty() { throw unsupported(); }
    public boolean         contains(Object object) { throw unsupported(); }
    public Iterator iterator() { throw unsupported(); }
    public Object[]                      toArray() { throw unsupported(); }
    public Object[]        toArray(Object[] array) { throw unsupported(); }
    public boolean              add(Object object) { throw unsupported(); }
    public boolean           remove(Object object) { throw unsupported(); }
    public void                            clear() { throw unsupported(); }
    public Object set(int location, Object object) { throw unsupported(); }
    public void   add(int location, Object object) { throw unsupported(); }
    public Object             remove(int location) { throw unsupported(); }
    public int              indexOf(Object object) { throw unsupported(); }
    public int          lastIndexOf(Object object) { throw unsupported(); }
    public ListIterator listIterator() { throw unsupported(); }
    public ListIterator listIterator(int location) { throw unsupported(); }
    public List subList(int start, int end) { throw unsupported(); }
    public boolean          containsAll(Collection collection) { throw unsupported(); }
    public boolean               addAll(Collection collection) { throw unsupported(); }
    public boolean addAll(int location, Collection collection) { throw unsupported(); }
    public boolean            removeAll(Collection collection) { throw unsupported(); }
    public boolean            retainAll(Collection collection) { throw unsupported(); }

    private RuntimeException unsupported() {
        return new RuntimeException("Not supported yet.");
    }

}
