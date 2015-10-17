package google;

import x.app.Registry;
import x.json.JsonList;
import x.json.JsonMap;
import x.json.JsonValue;
import x.json.XJSONParser;
import x.log.ILog;
import x.log.ILogManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract class JsonResponseParser<T>
{
    final List<T> parseJsonResponse(InputStreamReader reader) {
        List<T> list = new ArrayList<T>();
        try {
            for (JsonMap result : results((JsonMap) XJSONParser.parse(reader))) {
                list.add(construct(result));
            }
            return list;
        } catch (IOException e) {
            log(e);
        } catch (RuntimeException e) {
            log(e);
        }
        return list;
    } 
    
    final Iterable<JsonMap> results(JsonMap tree) {
        if (tree.isEmpty()) {
            log("Response Parser -- No results found -- failed request");
            return new ArrayList();
        }
        return (List)tree.get("results");
    }
    
    abstract T construct(JsonMap map);
    
    final String stringFrom(JsonMap map, String key) {
        return map.get(key).toString();
    }

    final Double doubleFrom(JsonMap map, String key) {
        JsonValue value = (JsonValue) map.get(key);
        return value.doubleValue();
    }

    final Long longFrom(JsonMap map, String key) {
        JsonValue value = (JsonValue) map.get(key);
        return value.longValue();
    }

    final URI uriFrom(JsonMap map, String key) {
        String string = stringFrom(map,key);
        try {
            return (string==null) ? null : new URI(string);
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
