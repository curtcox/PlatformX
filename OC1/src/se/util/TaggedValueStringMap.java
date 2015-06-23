package se.util;

import common.page.PageTags;
import common.screen.dynamic.TaggedStringSources;
import common.util.StringMap;

public interface TaggedValueStringMap
    extends StringMap, TaggedStringSources
{
    TaggedValue newValue();
    TaggedValue[] getValuesFor(PageTags tags);
}
