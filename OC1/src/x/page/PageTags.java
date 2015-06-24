package x.page;

import x.util.Strings;

import java.util.HashSet;
import java.util.Set;

/**
 * Immutable set of screen tags.
 */
public final class PageTags {

    final String tags;
    final Set<String> set;

    private PageTags(String tags) {
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

    public static PageTags of(String tags) {
        return new PageTags(tags.toLowerCase());
    }

    public boolean matches(PageLink link) {
        return set.containsAll(link.tags.set);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        PageTags that = (PageTags) o;
        return tags.equals(that.tags);
    }

    @Override
    public String toString() {
        return tags;
    }

}
