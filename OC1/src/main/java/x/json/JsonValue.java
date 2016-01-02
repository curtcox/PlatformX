package x.json;

/**
 * A string, double, long, or boolean.
 */
public final class JsonValue
    implements Json
{
    private final String value;
    private Double doubleValue;
    private Long longValue;
    private Boolean booleanValue;

    private JsonValue(String value) {
        this.value = value;
    }

    Comparable value() {
        if (doubleValue!=null) {
            return doubleValue;
        }
        if (longValue!=null) {
            return longValue;
        }
        if (booleanValue!=null) {
            return booleanValue;
        }
        return value;
    }

    static JsonValue of(String value) {
        return new JsonValue(value);
    }

    public Double doubleValue() {
        return doubleValue !=null ? doubleValue : longValue;
    }

    public Long longValue() {
        return longValue;
    }

    public Boolean booleanValue() {
        return booleanValue;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        JsonValue that = (JsonValue) o;
        return value.equals(that.value);
    }

    @Override
    public String toString() {
        return value;
    }

    static JsonValue of(Object o) {
        if (o instanceof String) {
            return of(o.toString());
        }
        if (o instanceof Double) {
            return of(o.toString(),(Double) o);
        }
        if (o instanceof Long) {
            return of(o.toString(),(Long) o);
        }
        if (o instanceof Boolean) {
            return of(o.toString(),(Boolean) o);
        }
        throw new IllegalArgumentException(o.getClass().toString());
    }

    static JsonValue of(String input, Double doubleValue) {
        JsonValue json = new JsonValue(input);
        json.doubleValue = doubleValue;
        return json;
    }

    static JsonValue of(String input, Long longValue) {
        JsonValue json = new JsonValue(input);
        json.longValue = longValue;
        return json;
    }

    static JsonValue of(String input, Boolean booleanValue) {
        JsonValue json = new JsonValue(input);
        json.booleanValue = booleanValue;
        return json;
    }

}
