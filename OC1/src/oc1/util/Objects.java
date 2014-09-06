package oc1.util;

import java.util.Arrays;

/**
 *
 * @author Curt
 */
public final class Objects {

    public static boolean areEqual(Object[] a, Object[] b) {
        return Arrays.asList(a).equals(Arrays.asList(b));
    }
    
}
