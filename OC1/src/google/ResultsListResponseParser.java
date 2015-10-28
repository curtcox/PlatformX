package google;

import x.app.Registry;
import x.json.Json;
import x.json.JsonList;
import x.json.JsonMap;
import x.json.XJSONParser;
import x.log.ILog;
import x.log.ILogManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

final class ResultsListResponseParser<T>
{
    final JsonItemConstructor<T> itemParser;

    ResultsListResponseParser(JsonItemConstructor<T> itemParser) {
        this.itemParser = itemParser;
    }

    final List<T> parseJsonResponse(InputStreamReader reader) {
        List<T> list = new ArrayList<T>();
        try {
            for (Json json : results(XJSONParser.parse(reader))) {
                JsonMap result = (JsonMap) json;
                list.add(itemParser.construct(result));
            }
            return list;
        } catch (IOException e) {
            log(e);
        } catch (RuntimeException e) {
            log(e);
        }
        return list;
    } 
    
    final JsonList<JsonMap> results(Json json) {
        if (!(json instanceof JsonMap)) {
            log("Response Parser -- Expected map, not found -- failed request");
            return JsonList.of(new ArrayList());
        }
        JsonMap tree = (JsonMap) json;
        if (tree.isEmpty()) {
            log("Response Parser -- No results found -- failed request");
            return JsonList.of(new ArrayList());
        }
        return tree.list("results");
    }

    private void log(Exception e) {
        getLog().log(e);    
    }

    private void log(String message) {
        getLog().log(message);    
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(this);
    }

}
