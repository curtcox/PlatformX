package x.json;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * An immutable list of Json.
 */
public final class JsonList<T extends Json>
    implements Json, List<Json>
{
    private List<Json> list;

    private JsonList(List list) {
        this.list = list;
    }

    public static JsonList of(List list) {
        return new JsonList(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Json get(int index) {
        return list.get(index);
    }

    @Override
    public Iterator<Json> iterator() {
        return list.iterator();
    }

    @Override
    public ListIterator<Json> listIterator() {
        return list.listIterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    public boolean equals(Object o) {
        JsonList that = (JsonList) o;
        return list.equals(that.list);
    }

    @Override
    public Json[] toArray() {
        throw never();
    }

    // ------------- Never --------------
    private UnsupportedOperationException never() {
        return new UnsupportedOperationException();
    }
    @Override
    public boolean contains(Object o) {
        throw never();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw never();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        throw never();
    }

    @Override
    public int indexOf(Object o) {
        throw never();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw never();
    }

    @Override
    public ListIterator<Json> listIterator(int index) {
        throw never();
    }

    @Override
    public List<Json> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends Json> c) {
        throw never();
    }

    @Override
    public boolean addAll(int index, Collection<? extends Json> c) {
        throw never();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw never();
    }

    @Override
    public boolean add(Json json) {
        throw never();
    }

    @Override
    public boolean remove(Object o) {
        throw never();
    }

    @Override
    public Json set(int index, Json element) {
        throw never();
    }

    @Override
    public void add(int index, Json element) {
        throw never();
    }

    @Override
    public Json remove(int index) {
        throw never();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw never();
    }


}
