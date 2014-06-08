
package myapp.util;

/**
 *
 * @author Curt
 */
public final class Strings {
    
    public static String[] split(String string,String token) {
        int at = string.indexOf(token);
        if (at<=0) {
            return new String[] {string};
        }
        String head = string.substring(0,at);
        String rest = string.substring(at+1, string.length());
        String[] tail = split(rest,token);
        if (tail.length==1) {
            return new String[] {head,rest};
        }
        String[] all = new String[tail.length + 1];
        all[0] = head;
        for (int i=0; i<tail.length; i++) {
            all[i+1] = tail[i];
        }
        return all;
    }

}
