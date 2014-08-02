package oc1.event;

import java.util.List;

/**
 *
 * @author Curt
 */
public interface SwappableList<T> 
    extends LiveList<T>
{    
    void become(List<T> list);
}
