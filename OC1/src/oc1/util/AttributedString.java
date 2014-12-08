package oc1.util;

import com.codename1.ui.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A String with fonts, colors, and markup.
 * An attributed string will either have homogenous attributes, or defined in
 * terms of other attributed strings.
 * @author Curt
 */
public final class AttributedString
    implements Iterable<AttributedString>
{
    
    private final String text;
    
    /**
     * The (possibly null) attributes of this string.
     */
    public final Attributes attributes;
 
    /**
     * True if the string has entirely homogenous attributes and false otherwise.
     */
    public final boolean simple;
    private final List<AttributedString> parts;

    public Iterator<AttributedString> iterator() {
        return parts.iterator();
    }
    
    public static final class Attributes {
        public final Font font;
        public final Color color;
        
        Attributes(Font font, Color color) {
            this.font = font;
            this.color = color;
        }
        
        @Override
        public int hashCode() {
            return 0;
        }
        
        @Override
        public boolean equals(Object o) {
            Attributes that = (Attributes) o;
            return Objects.areEqual(font, that.font) &&
                   Objects.areEqual(color, that.color);
        }
    }
            
    private AttributedString(Builder builder) {
        this.simple = builder.simple;
        this.text = builder.text.toString();
        this.attributes = builder.attributes();
        this.parts = builder.parts;
    }
    
    public static final class Builder {
        StringBuilder text = new StringBuilder();
        List<AttributedString> parts = new ArrayList<AttributedString>();
        Attributes current;
        Attributes prior;
        Color color;
        Font font;
        boolean simple = true;

        AttributedString build() {
            if (simple || parts.size() < 2) {
                parts.clear();
            }
            return new AttributedString(this);
        }

        Builder append(String text) {
            this.text.append(text);
            if (parts.isEmpty()) {
                parts.add(new AttributedString(this));
            } else if (Objects.areEqual(prior,current)) {
                parts.set(parts.size()-1, new AttributedString(this));
            } else {
                parts.add(new AttributedString(this));
            }
            if (parts.size()>1) {
                simple = false;
            }
            prior = current;
            current = currentAttributes();
            return this;
        }

        Builder font(Font font) {
            this.font = font;
            return this;
        }

        Builder color(Color color) {
            this.color = color;
            current = currentAttributes();
            return this;
        }

        private Attributes currentAttributes() {
            if (color==null && font==null) {
                return null;
            }
            return new Attributes(font,color);
        }
        
        private Attributes attributes() {
            if (color==null && font==null || !parts.isEmpty()) {
                return null;
            }
            return new Attributes(font,color);
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
