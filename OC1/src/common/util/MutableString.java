package common.util;

public final class MutableString {

    private String value;

    public MutableString() {
        this("");
    }

    public MutableString(String value) {
        this.value = value;
    }


    public void set(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
