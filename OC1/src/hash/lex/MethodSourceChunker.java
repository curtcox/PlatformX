package hash.lex;

import java.util.ArrayList;
import java.util.List;

/**
 * For splitting Hash source into method-sized chunks.
 * @author Curt
 */
public final class MethodSourceChunker {

    public static String[] split(String source) {
        List<String> methods = new ArrayList<String>();
        StringBuilder out = new StringBuilder();
        for (int i=0; i<source.length(); i++) {
            String c = source.substring(i, i+1);
            out.append(c);
            if (c.equals("}")) {
                methods.add(out.toString());
                out = new StringBuilder();
            }
        }
        return methods.toArray(new String[0]);
    }
    
}
