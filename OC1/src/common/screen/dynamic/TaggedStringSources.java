package common.screen.dynamic;

import common.event.StringSource;
import common.page.ScreenTags;

/**
 * A way to get strings marked by tags.
 */
public interface TaggedStringSources {

    StringSource[] get(ScreenTags tags);
}
