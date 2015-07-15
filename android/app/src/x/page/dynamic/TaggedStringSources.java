package x.page.dynamic;

import x.event.StringSource;
import x.page.PageTags;

/**
 * A way to get strings marked by tags.
 */
public interface TaggedStringSources {

    StringSource[] get(PageTags tags);
}
