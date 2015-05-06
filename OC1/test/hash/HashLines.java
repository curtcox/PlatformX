package hash;

/**
 * Test utility for building a Hash source string from lines.
 * @author Curt
 */
public class HashLines {

    public static String from(String...lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line + " \r\n");
        }
        return out.toString().replaceAll("'", "\"");
    }
    
}
