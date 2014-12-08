package oc1.util;

import com.codename1.ui.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A String with fonts, colors, and markup.
 * @author Curt
 */
public final class AttributedString
    implements Iterable<AttributedString.Part>
{
    /**
     * The parts of this string.
     */
    public final List<Part> parts;
    private final String text;
 
    public Iterator<Part> iterator() {
        return parts.iterator();
    }
    
    public static final class Part {
        public final Font font;
        public final Color color;
        public final String text;
        
        Part(Font font, Color color, String text) {
            this.font = font;
            this.color = color;
            this.text = text;
        }
        
        @Override
        public int hashCode() {
            return 0;
        }
        
        @Override
        public boolean equals(Object o) {
            Part that = (Part) o;
            return Objects.areEqual(font, that.font) &&
                   Objects.areEqual(color, that.color) &&
                   Objects.areEqual(text,that.text);
        }
    }
            
    private AttributedString(Builder builder) {
        this.parts = builder.parts;
        this.text = builder.text.toString();
    }
    
    public static final class Builder {
        StringBuilder text = new StringBuilder();
        List<Part> parts = new ArrayList<Part>();
        Set current;
        Set prior;
        Color color;
        Font font;

        AttributedString build() {
            return new AttributedString(this);
        }

        Builder append(String text) {
            this.text.append(text);
            if (parts.isEmpty()) {
                parts.add(new Part(font,color,text));
            } else if (Objects.areEqual(prior,current)) {
                parts.set(parts.size()-1, new Part(font,color,text));
            } else {
                parts.add(new Part(font,color,text));
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

        private Set currentAttributes() {
            if (color==null && font==null) {
                return null;
            }
            return new HashSet(Arrays.asList(font,color));
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
