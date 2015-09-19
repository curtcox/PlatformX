package x.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public final class CollectionAsList<T>
    implements List<T>
{
    final Collection collection;

    public CollectionAsList(Collection collection) {
        this.collection = collection;
    }

    @Override public int size() {
        return collection.size();
    }

    @Override public T get(int index) {
        return (T) collection.toArray()[index];
    }

    @Override public boolean isEmpty() {throw new UnsupportedOperationException();}
    @Override public boolean contains(Object o) {throw new UnsupportedOperationException();}
    @Override public Iterator iterator() {throw new UnsupportedOperationException();}
    @Override public Object[] toArray() {throw new UnsupportedOperationException();}
    @Override public boolean add(Object o) {throw new UnsupportedOperationException();}
    @Override public boolean remove(Object o) {throw new UnsupportedOperationException();}
    @Override public boolean addAll(Collection c) {throw new UnsupportedOperationException();}
    @Override public boolean addAll(int index, Collection c) {throw new UnsupportedOperationException();}
    @Override public void clear() {throw new UnsupportedOperationException();}
    @Override public Object set(int index, Object element) {throw new UnsupportedOperationException();}
    @Override public void add(int index, Object element) {throw new UnsupportedOperationException();}
    @Override public T remove(int index) {throw new UnsupportedOperationException();}
    @Override public int indexOf(Object o) {throw new UnsupportedOperationException();}
    @Override public int lastIndexOf(Object o) {throw new UnsupportedOperationException();}
    @Override public ListIterator listIterator() {throw new UnsupportedOperationException();}
    @Override public ListIterator listIterator(int index) {throw new UnsupportedOperationException();}
    @Override public List subList(int fromIndex, int toIndex) {throw new UnsupportedOperationException();}
    @Override public boolean retainAll(Collection c) {throw new UnsupportedOperationException();}
    @Override public boolean removeAll(Collection c) {return false;}
    @Override public boolean containsAll(Collection c) {throw new UnsupportedOperationException();}
    @Override public T[] toArray(Object[] a) {throw new UnsupportedOperationException();}
}
