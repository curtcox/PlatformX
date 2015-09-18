package x.page;

import x.util.Strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Immutable set of screen tags.
 */
public final class PageTags {

    final String tags;
    final Set<String> set;

    private PageTags(String tags) {
        this(tags, splitIntoSet(tags));
    }

    private PageTags(String tags,Set<String> set) {
        this.tags = tags;
        this.set = set;
    }

    private static Set<String> splitIntoSet(String tags) {
        return combineInSet(Strings.split(tags," "));
    }

    public static PageTags of(String... tags) {
        return new PageTags(join(tags),combineInSet(tags));
    }

    private static Set<String> combineInSet(String[] tags) {
        Set<String> set = new HashSet();
        for (String tag : tags) {
            if (!Strings.isEmpty(tag)) {
                set.add(tag.toLowerCase());
            }
        }
        return set;
    }

    private static String join(String[] tags) {
        StringBuilder out = new StringBuilder();
        for (String tag : tags) {
            out.append(tag.toLowerCase() + " ");
        }
        return out.toString().trim();
    }

    public static PageTags of(String tags) {
        return new PageTags(tags.toLowerCase());
    }

    public boolean matches(PageLink link) {
        return set.containsAll(link.tags.set);
    }

    public boolean matches(PageTags tags) {
        return set.containsAll(tags.set);
    }

    public PageTags plus(String tag) {
        List<String> all = new ArrayList<String>();
        all.addAll(set);
        all.add(tag);
        return PageTags.of(all.toArray(new String[0]));
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
