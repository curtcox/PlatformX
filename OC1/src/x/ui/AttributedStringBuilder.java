package x.ui;

import x.util.Objects;

import java.util.*;

public final class AttributedStringBuilder {
    StringBuilder text = new StringBuilder();
    List<AttributedString.Part> parts = new ArrayList<AttributedString.Part>();
    List<String> partsText = new ArrayList<String>();
    Set current;
    Set prior;
    Color color;
    UIFont font;
    AttributedString.Decoration decoration;

    public AttributedString build() {
        return new AttributedString(text.toString(),parts);
    }

    public AttributedStringBuilder append(String string) {
        this.text.append(string);
        if (!(Objects.areEqual(prior, current))) {
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

    AttributedString.Part newPart() {
        StringBuilder out = new StringBuilder();
        for (String string : partsText) {
            out.append(string);
        }
        return new AttributedString.Part(font,color,decoration,out.toString());
    }

    public AttributedStringBuilder font(UIFont font) {
        this.font = font;
        current = currentAttributes();
        return this;
    }

    public AttributedStringBuilder decoration(AttributedString.Decoration decoration) {
        this.decoration = decoration;
        current = currentAttributes();
        return this;
    }

    public AttributedStringBuilder color(Color color) {
        this.color = color;
        current = currentAttributes();
        return this;
    }

    private Set currentAttributes() {
        if (color==null && font==null && decoration==null) {
            return null;
        }
        return new HashSet(Arrays.asList(font, color, decoration));
    }

}
