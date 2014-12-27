package common.event;

import oc1.event.LiveList;

import java.util.List;

public interface SwappableList<T>
    extends LiveList<T>
{    
    void become(List<T> list);
}
