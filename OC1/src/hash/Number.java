package hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Curt
 */
final class Number {

    static final String[] VALID = new String[] {
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };
    
    private static final Set<String> accept = new HashSet(Arrays.asList(VALID));
    
    static boolean isValid(String value) {
        for (int i=0; i<value.length(); i++) {
            String c = value.substring(i, i+1);
            if (!accept.contains(c)) {
                return false;
            }
        }
        return true;
    }

}
