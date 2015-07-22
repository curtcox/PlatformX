package x.event;

/**
 * A list of values that could change at any time.
 */
public interface LiveList<T> 
    extends Change.Source, java.util.List<T>
{
    
}
