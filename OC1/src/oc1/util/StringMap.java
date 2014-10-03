package oc1.util;

import java.util.HashMap;

/**
 * A map from strings to strings.
 * @author Curt
 */
public interface StringMap {

    interface Parser {
        StringMap parse(String string);    
    }
    
    String get(String string);
    
    static final StringMap EMPTY = new SimpleStringMap(new HashMap());
}
