package common.util;

/**
 * A map from strings to strings.
 * This is a read-only abstraction that could be backed by for a HashMap,
 * a file, a file system, a remote server, or some combination of the 
 * previous.
 * @author Curt
 */
public interface StringMap {

    /**
     * For parsing a String into a StringMap.
     */
    interface Parser {
        StringMap parse(String string);    
    }
    
    String get(String key);
}
