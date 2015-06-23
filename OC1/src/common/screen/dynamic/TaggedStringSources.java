package common.screen.dynamic;

import common.event.StringSource;
import common.page.PageTags;

/**
 * A way to get strings marked by tags.
 */
public interface TaggedStringSources {

    StringSource[] get(PageTags tags);
}
