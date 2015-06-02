package common.event;

import java.util.List;

public interface SwappableList<T>
    extends LiveList<T>
{
    interface Factory {
        SwappableList from(List list);
    }

    void become(List<T> list);
}
