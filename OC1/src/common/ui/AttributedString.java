package common.ui;

import common.util.Objects;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

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

    AttributedString(String text,List<Part> parts) {
        this.parts = parts;
        this.text = text;
    }

    public Iterator<Part> iterator() {
        return parts.iterator();
    }

    @Override
    public String toString() {
        return text;
    }

    public enum Decoration {
        None,Underline,Strikethru,Overline,ShadowNorth3D,Normal3D,Lowered3D
    }

    public interface Renderer {
        void renderPartAt(AttributedString.Part part,Point point);
        Dimension size(AttributedString.Part part);
    }

    public static final class Part {
        public final UIFont font;
        public final Color color;
        public final String text;
        public final Decoration decoration;
        public final int size;

        Part(UIFont font, Color color, Decoration decoration, String text) {
            this.font = font;
            this.color = color;
            this.text = text;
            this.decoration = decoration;
            this.size = text==null ? 0 : text.length();
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

        public Part minusPrefix(Part first) {
            return null;
        }

        public Part prefixOfSize(int i) {
            return null;
        }
    }


}
