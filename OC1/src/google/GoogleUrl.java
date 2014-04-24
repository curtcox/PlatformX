package google;

import java.util.Map;

/**
 *
 * @author Curt
 */
final class GoogleUrl {
    
    static String of(String base,Map<String,String> parameters) {
        StringBuilder out = new StringBuilder(base);
        for (String key : parameters.keySet()) {
            out.append(key + "=" + parameters.get(key) + "&");
        }
        return out.toString();
    }
}
