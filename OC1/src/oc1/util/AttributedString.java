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


    public static final Builder builder() {
        return new Builder();
    }
    
    @Override
    public String toString() {
        return text;
    }

    public enum Decoration {
        None,Underline,Strikethru,Overline,ShadowNorth3D,Normal3D,Lowered3D
    }

    public static final class Part {
        public final Font font;
        public final Color color;
        public final String text;
        public final Decoration decoration;
        
        Part(Font font, Color color, Decoration decoration, String text) {
            this.font = font;
            this.color = color;
            this.text = text;
            this.decoration = decoration;
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
                   Objects.areEqual(decoration, that.decoration) &&
                   Objects.areEqual(text,that.text);
        }
        
        @Override
        public String toString() {
            return  "text=" + text +
                    " color=" + color +
                    " decoration=" + decoration +
                    " font=" + font
            ;
        }
    }
            
    private AttributedString(Builder builder) {
        this.parts = builder.parts;
        this.text = builder.text.toString();
    }
    
    public static final class Builder {
        StringBuilder text = new StringBuilder();
        List<Part> parts = new ArrayList<Part>();
        List<String> partsText = new ArrayList<String>();
        Set current;
        Set prior;
        Color color;
        Font font;
        Decoration decoration;

        AttributedString build() {
            return new AttributedString(this);
        }

        Builder append(String string) {
            this.text.append(string);
            if (!(Objects.areEqual(prior,current))) {
                partsText = new ArrayList<String>();
            }
            partsText.add(string);
            if (parts.isEmpty() || !(Objects.areEqual(prior,current))) {
                parts.add(newPart());
            } else {
                parts.set(parts.size()-1, newPart());
            }
            prior = current;
            current = currentAttributes();
            return this;
        }

        Part newPart() {
            StringBuilder out = new StringBuilder();
            for (String string : partsText) {
                out.append(string);
            }
            return new Part(font,color,decoration,out.toString());
        }
        
        Builder font(Font font) {
            this.font = font;
            current = currentAttributes();
            return this;
        }

        Builder decoration(Decoration decoration) {
            this.decoration = decoration;
            current = currentAttributes();
            return this;
        }

        Builder color(Color color) {
            this.color = color;
            current = currentAttributes();
            return this;
        }

        private Set currentAttributes() {
            if (color==null && font==null && decoration==null) {
                return null;
            }
            return new HashSet(Arrays.asList(font,color,decoration));
        }
        
    }
    
}
