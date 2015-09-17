package x.pagefactories;

public final class NamedValue {

    public final String name;
    public final Object value;
    
    public NamedValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return name + "=" + value;
    }
}
