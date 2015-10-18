package google;

import x.app.Registry;
import x.net.Network;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import java.util.Map;

abstract class WebServiceSearch<T> {
    
    final String baseURI;
    final ResultsListResponseParser<T> parser;
    
    WebServiceSearch(String baseURI, JsonItemConstructor<T> parser) {
        this.baseURI = baseURI;
        this.parser = new ResultsListResponseParser<T>(parser);
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
