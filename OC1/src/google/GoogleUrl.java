package google;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 *
 * @author Curt
 */
final class GoogleUrl {
    
    static URI of(String base,Map<String,String> parameters) {
        try {
            StringBuilder out = new StringBuilder(base);
            for (String key : parameters.keySet()) {
                out.append(key + "=" + parameters.get(key) + "&");
            }
            return new URI(out.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
