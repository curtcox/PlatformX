package google;

import com.codename1.io.JSONParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.log.ILog;
import common.log.ILogManager;
import common.Registry;
import c1.ui.C1Icons;

abstract class JsonResponseParser<T>
{
    final List<T> parseJsonResponse(InputStreamReader reader) {
        List<T> location = new ArrayList<T>();
        try {
            JSONParser parser = new JSONParser();
            for (Map<String,Object> result : results(parser.parseJSON(reader))) {
                location.add(construct(result));
            }
            return location;
        } catch (IOException e) {
            log(e);
        } catch (RuntimeException e) {
            log(e);
        }
        return location;
    } 
    
    final Iterable<Map<String,Object>> results(Map<String,Object> tree) {
        if (tree.isEmpty()) {
            log("Response Parser -- No results found -- failed request");
            return new ArrayList();
        }
        return (List)tree.get("results");
    }
    
    abstract T construct(Map<String,Object> map);
    
    final String stringFrom(Map<String,Object> map, String key) {
        return (String) map.get(key);
    }

    final Double doubleFrom(Map<String,Object> map, String key) {
        return (Double) map.get(key);
    }

    final URI uriFrom(Map<String,Object> map, String key) {
        String string = stringFrom(map,key);
        try {
            URI uri = (string==null) ? null : new URI(string);
            C1Icons.of().getImage(uri);
            return uri;
        } catch (URISyntaxException e) {
            log(e);
            return null;
        }
    }
    
    private void log(Exception e) {
        getLog().log(e);    
    }

    private void log(String message) {
        getLog().log(message);    
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(JsonResponseParser.class);
    }

}
