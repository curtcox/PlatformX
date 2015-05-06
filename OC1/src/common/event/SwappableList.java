package common.event;

import c1.event.LiveList;

import java.util.List;

public interface SwappableList<T>
    extends LiveList<T>
{    
    void become(List<T> list);
}
