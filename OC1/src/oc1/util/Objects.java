package oc1.util;

import java.util.Arrays;

/**
 * Useful methods for ObjectS.
 * @author Curt
 */
public final class Objects {

    private static boolean areEqualArrays(Object[] a, Object[] b) {
        return Arrays.asList(a).equals(Arrays.asList(b));
    }

    public static boolean areEqual(Object a, Object b) {
        if (a==null && b==null) {
            return true;
        }
    
        if ((a==null) != (b==null)) {
            return false;
        }
        
        if (a instanceof Object[] && b instanceof Object[]) {
            return areEqualArrays((Object[])a,(Object[])b);
        }
        
        return a.equals(b);
    }

}
