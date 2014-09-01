package oc1.util;

/**
 *
 * @author Curt
 */
public final class Splitter {

    public static String[] split(String string,String token) {
        int at = string.indexOf(token);
        if (at<=0) {
            return new String[] {string};
        }
        return join(before(string,at),split(after(string,at),token));
    }

    private static String before(String string,int at) {
        return string.substring(0,at);
    }

    private static String after(String string,int at) {
        return string.substring(at+1, string.length());
    }

    private static String[] join(String head, String[] tail) {
        String[] all = new String[tail.length + 1];
        all[0] = head;
        for (int i=0; i<tail.length; i++) {
            all[i+1] = tail[i];
        }
        return all;
    }
}
