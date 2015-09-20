
package x.util;

public final class Strings {
    
    public static String[] split(String string,String token) {
        return Splitter.split(string, token);
    }
    
    public static boolean contains(String containing, String contained) {
        return containing.indexOf(contained) >= 0; 
    }

    public static String replace(String string, String original, String replacement) {
        StringBuilder out = new StringBuilder();
        for (int i=0; i<string.length(); i++) {
            String s = string.substring(i, i+1);
            if (s.equals(original)) {
                out.append(replacement);
            } else {
                out.append(s);
            }
        }
        return out.toString();
    }

    public static boolean isEmpty(String string) {
        return string.length() == 0;
    }

    public static String elided(String title) {
        if (title.length()<=100) {
            return title;
        }
        return title.substring(0,97) + "...";
    }
    
    public static CharSequence asChars(String string) {
        return string.subSequence(0,string.length());
    }
}
