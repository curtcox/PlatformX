package x.io;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public interface XJSON {
    Map<String, Object> parseJSON(Reader reader) throws IOException;
}