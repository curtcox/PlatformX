package x.parse;

/**
 * For splitting a string into tokens.
 * See also Lexer, which wraps Tokenizer to correctly handle quotes.
 * @author Curt
 */
public final class Tokenizer {

    /**
     * Use the separators to split the string into tokens.
     * The separators are included in the returned tokens.
     * @param string the string to be spilt
     * @param separators the separator tokens to split on
     * @return the separator tokens and the strings between them.
     */
    public static String[] tokenize(String string,String... separators) {
        int at = startOfFirst(string,separators);
        if (at<0) {
            return (string.length()==0) ? new String[0] : new String[] {string};
        }
        return join(before(string,at),at(string,at),tokenize(after(string,at),separators));
    }

    private static int startOfFirst(String string, String... tokens) {
        int min = Integer.MAX_VALUE;
        for (String token : tokens) {
            int at = string.indexOf(token);
            if (at>=0 && at<min) {
                min = at;
            }
        }
        return (min == Integer.MAX_VALUE) ? -1 : min;
    }
    
    private static String before(String string,int at) {
        return string.substring(0,at);
    }

    private static String at(String string,int at) {
        return string.substring(at, at + 1);
    }

    private static String after(String string,int at) {
        return string.substring(at+1, string.length());
    }

    private static String[] join(String head, String node, String[] tail) {
        if (head.length()>0 && node.length()>0) {
            return joinHeadNodeTail(head,node,tail);
        }
        if (head.length()>0) {
            return join(head,tail);
        }
        if (node.length()>0) {
            return join(node,tail);
        }
        return tail;
    }

    private static String[] joinHeadNodeTail(String head, String node, String[] tail) {
        String[] all = new String[tail.length + 2];
        all[0] = head;
        all[1] = node;
        for (int i=0; i<tail.length; i++) {
            all[i+2] = tail[i];
        }
        return all;
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
