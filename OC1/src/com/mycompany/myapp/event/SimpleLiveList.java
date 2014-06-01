package com.mycompany.myapp.event;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.util.EventDispatcher;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Curt
 */
public final class SimpleLiveList
    implements LiveList, DataChangedListener 
{
    private final List list;
    private final EventDispatcher dataListeners = new EventDispatcher();

    public SimpleLiveList(List list) {
        this.list = list;
    }
    
    public void addDataChangedListener(DataChangedListener listener) {
        dataListeners.addListener(listener);
    }

    public void removeDataChangedListener(DataChangedListener listener) {
        dataListeners.removeListener(listener);
    }
    
    public void dataChanged(int type, int index) {
        dataListeners.fireDataChangeEvent(index, type);
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

    public boolean isEmpty() {
        throw new RuntimeException("Not supported yet.");
    }

    public boolean contains(Object object) {
        throw new RuntimeException("Not supported yet.");
    }

    public Iterator iterator() {
        throw new RuntimeException("Not supported yet.");
    }

    public Object[] toArray() {
        throw new RuntimeException("Not supported yet.");
    }

    public Object[] toArray(Object[] array) {
        throw new RuntimeException("Not supported yet.");
    }

    public boolean add(Object object) {
        throw new RuntimeException("Not supported yet.");
    }

    public boolean remove(Object object) {
        throw new RuntimeException("Not supported yet.");
    }

    public boolean containsAll(Collection collection) {
        throw new RuntimeException("Not supported yet.");
    }

    public boolean addAll(Collection collection) {
        throw new RuntimeException("Not supported yet.");
    }

    public boolean addAll(int location, Collection collection) {
        throw new RuntimeException("Not supported yet.");
    }

    public boolean removeAll(Collection collection) {
        throw new RuntimeException("Not supported yet.");
    }

    public boolean retainAll(Collection collection) {
        throw new RuntimeException("Not supported yet.");
    }

    public void clear() {
        throw new RuntimeException("Not supported yet.");
    }

    public Object set(int location, Object object) {
        throw new RuntimeException("Not supported yet.");
    }

    public void add(int location, Object object) {
        throw new RuntimeException("Not supported yet.");
    }

    public Object remove(int location) {
        throw new RuntimeException("Not supported yet.");
    }

    public int indexOf(Object object) {
        throw new RuntimeException("Not supported yet.");
    }

    public int lastIndexOf(Object object) {
        throw new RuntimeException("Not supported yet.");
    }

    public ListIterator listIterator() {
        throw new RuntimeException("Not supported yet.");
    }

    public ListIterator listIterator(int location) {
        throw new RuntimeException("Not supported yet.");
    }

    public List subList(int start, int end) {
        throw new RuntimeException("Not supported yet.");
    }
    
}
