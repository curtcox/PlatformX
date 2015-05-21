package common.screen;

import java.util.Arrays;

/**
 * A link to a screen.  This is analogous to an HTML link.
 */
public final class ScreenLink {

    public interface Factory {
        ScreenLink create();
    }

    public final ScreenTags tags;
    public final Object[] args;

    private ScreenLink(String screen, Object... args) {
        this.tags = ScreenTags.of(screen);
        this.args = args;
    }

    public static ScreenLink of(String name, Object... args) {
        return new ScreenLink(name,args);
    }

    public String title() {
        return tags.toString();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        ScreenLink that = (ScreenLink) o;
        return tags.equals(that.tags);
    }

    @Override
    public String toString() {
        return tags + " " + Arrays.asList(args);
    }
}