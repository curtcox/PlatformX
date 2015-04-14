package common.screen;

/**
 * Immutable set of screen tags.
 */
public final class ScreenTags {

    final String tags;

    private ScreenTags(String tags) {
        this.tags = tags;
    }

    public static ScreenTags of(String tags) {
        return new ScreenTags(tags.toLowerCase());
    }

    public boolean matches(ScreenLink link) {
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        ScreenTags that = (ScreenTags) o;
        return tags.equals(that.tags);
    }

    @Override
    public String toString() {
        return tags;
    }

}
