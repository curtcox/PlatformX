package se.util;

import common.page.PageTags;
import common.page.dynamic.TaggedStringSources;
import common.util.StringMap;

public interface TaggedValueStringMap
    extends StringMap, TaggedStringSources
{
    TaggedValue newValue();
    TaggedValue[] getValuesFor(PageTags tags);
}
