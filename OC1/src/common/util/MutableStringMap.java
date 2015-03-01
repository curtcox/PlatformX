package common.util;

public interface MutableStringMap
    extends StringMap
{
    void put(String key, String value);
    void delete(String key);
}
