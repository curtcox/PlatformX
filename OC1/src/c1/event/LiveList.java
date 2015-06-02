package c1.event;

import common.event.Change;

/**
 * A list of values that could change at any time.
 */
public interface LiveList<T> 
    extends Change.Source, java.util.List<T>
{
    
}
