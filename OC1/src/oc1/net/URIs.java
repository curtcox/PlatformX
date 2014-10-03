package oc1.net;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Curt
 */
public final class URIs {
        
    public static URI URI(String string) {
        try {
            return new URI(string);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(string);
        }
    }

}
