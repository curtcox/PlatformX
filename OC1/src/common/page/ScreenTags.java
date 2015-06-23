package common.page;

import common.util.Strings;
import java.util.HashSet;
import java.util.Set;

/**
 * Immutable set of screen tags.
 */
public final class ScreenTags {

    final String tags;
    final Set<String> set;

    private ScreenTags(String tags) {
        this.tags = tags;
        set = splitIntoSet(tags);
    }

    private static Set<String> splitIntoSet(String tags) {
        Set<String> set = new HashSet();
        for (String tag : Strings.split(tags," ")) {
            if (!Strings.isEmpty(tag)) {
                set.add(tag);
            }
        }
        return set;
    }

    public static ScreenTags of(String tags) {
        return new ScreenTags(tags.toLowerCase());
    }

    public boolean matches(ScreenLink link) {
        return set.containsAll(link.tags.set);
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
