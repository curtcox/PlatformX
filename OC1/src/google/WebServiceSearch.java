package google;

import x.Registry;
import x.net.Network;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import java.util.Map;

abstract class WebServiceSearch<T> {
    
    final String baseURI;
    final JsonResponseParser<T> parser;
    
    WebServiceSearch(String baseURI,JsonResponseParser parser) {
        this.baseURI = baseURI;
        this.parser = parser;
    }
    
    URI getURI(Map<String,String> parameters) {
        parameters.put("key",Google.API_key);
        return GoogleUrl.of(baseURI,parameters);
    }
    
    List<T> searchForPlaces(URI url) {
        return parser.parseJsonResponse(new InputStreamReader(getStreamFor(url)));
    }
    
    InputStream getStreamFor(URI url) {
        return Registry.get(Network.class).getStreamFor(url);
    }
}
