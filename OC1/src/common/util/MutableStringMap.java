package common.util;

public interface MutableStringMap
    extends StringMap
{
    void put(String key, String value);
    void rename(String oldKey, String newKey);
    void delete(String key);
    void addListener(Listener listener);

    interface Listener {
        void onUpdate(String key, String value);
        void onRename(String oldKey, String newKey);
        void onDelete(String key);
    }
}
