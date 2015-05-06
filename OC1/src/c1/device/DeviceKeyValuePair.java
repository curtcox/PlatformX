package c1.device;

public final class DeviceKeyValuePair {

    public final String key;
    public final Object value;
    
    DeviceKeyValuePair(String key, Object value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return key + "=" + value;
    }
}
