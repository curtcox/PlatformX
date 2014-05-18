package com.mycompany.myapp.event;

/**
 * A list of values that could change at any time.
 * @author Curt
 */
public interface LiveList<T> 
    extends DataChangeSource, java.util.List<T>
{
    
}
