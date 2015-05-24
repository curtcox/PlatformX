package c1.event;

/**
 * A list of values that could change at any time.
 */
public interface LiveList<T> 
    extends DataChangeSource, java.util.List<T>
{
    
}
