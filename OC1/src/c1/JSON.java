package c1;

import com.codename1.io.JSONParser;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.util.SimpleStringMap;
import x.util.StringMap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public final class JSON {

    public static final StringMap.Parser STRING_MAP_PARSER = new StringMap.Parser() {
        public StringMap parse(String string) {
            return new SimpleStringMap(parseJsonResponse(new InputStreamReader(new ByteArrayInputStream(string.getBytes()))));
        }
    };
    
    static final Map<String,String> parseJsonResponse(InputStreamReader reader) {
        Map<String,String> map = new HashMap<String,String>();
        try {
            JSONParser parser = new JSONParser();
            Map<String,Object> result = parser.parseJSON(reader);
            addAllResultsTo(result,map);
            return map;
        } catch (IOException e) {
            log(e);
        } catch (RuntimeException e) {
            log(e);
        }
        return map;
    } 

    private static void addAllResultsTo(Map<String, Object> result, Map<String, String> map) {
        for (String key : result.keySet()) {
            Object value = result.get(key);
            if (value!=null) {
                map.put(key, value.toString());
            }
        }
    }

    private static void log(Exception e) {
        getLog().log(e);    
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(JSON.class);
    }

}
