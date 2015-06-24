package x.event;

import java.util.*;

public final class CommonLiveList
    implements LiveList
{
    private final List list;
    private Change.Listener listener;

    public CommonLiveList(List list) {
        this.list = new ArrayList(list);
    }

    public CommonLiveList() {
        this(new ArrayList());
    }

    public void addListener(Change.Listener listener) {
        this.listener = listener;
    }

    public Object get(int index) {
        if (index<0) {
            return null;
        }
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean add(Object object) {
        boolean added = list.add(object);
        listener.onChange();
        return added;
    }

    public boolean                       isEmpty() { throw unsupported(); }
    public boolean         contains(Object object) { throw unsupported(); }
    public Iterator                     iterator() { throw unsupported(); }
    public Object[]                      toArray() { throw unsupported(); }
    public Object[]        toArray(Object[] array) { throw unsupported(); }
    public boolean           remove(Object object) { throw unsupported(); }
    public void                            clear() { throw unsupported(); }
    public Object set(int location, Object object) { throw unsupported(); }
    public void   add(int location, Object object) { throw unsupported(); }
    public Object             remove(int location) { throw unsupported(); }
    public int              indexOf(Object object) { throw unsupported(); }
    public int          lastIndexOf(Object object) { throw unsupported(); }
    public ListIterator             listIterator() { throw unsupported(); }
    public ListIterator listIterator(int location) { throw unsupported(); }
    public List        subList(int start, int end) { throw unsupported(); }
    public boolean          containsAll(Collection collection) { throw unsupported(); }
    public boolean               addAll(Collection collection) { throw unsupported(); }
    public boolean addAll(int location, Collection collection) { throw unsupported(); }
    public boolean            removeAll(Collection collection) { throw unsupported(); }
    public boolean            retainAll(Collection collection) { throw unsupported(); }
    
    private RuntimeException unsupported() {
        return new RuntimeException("Not supported yet.");
    }
}
