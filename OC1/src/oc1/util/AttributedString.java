package oc1.util;

/**
 * A String with fonts, colors, and markup.
 * @author Curt
 */
public final class AttributedString {
    
    public final boolean simple;
    final String text; 
            
    private AttributedString(Builder builder) {
        this.simple = builder.simple;
        this.text = builder.text;
    }
    
    public static final class Builder {
        String text = "";
        boolean simple = true;

        AttributedString build() {
            return new AttributedString(this);
        }

        Builder append(String text) {
            this.text = text;
            return this;
        }
    }
    
    public static final Builder builder() {
        return new Builder();
    }
    
    @Override
    public String toString() {
        return text;
    }
}
