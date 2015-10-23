package hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class Identifier {

    /**
     * Characters that cannot be part of an identifier
     */
    public static final String[] SPECIAL = new String[] {
        " ", "\t", "\r", "\n", 
        ".", "?", ":", ",", "^", "{", "}", "(", ")",
        "\"",
        "#"
    };
    
    private static final Set<String> reject = new HashSet(Arrays.asList(SPECIAL));
    
    public static boolean isValid(String value) {
        if (Number.isValid(value)) {
            return false;
        }
        for (int i=0; i<value.length(); i++) {
            String c = value.substring(i, i+1);
            if (reject.contains(c)) {
                return false;
            }
        }
        return true;
    }

}
