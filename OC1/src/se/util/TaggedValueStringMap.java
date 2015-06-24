package se.util;

import x.page.PageTags;
import x.page.dynamic.TaggedStringSources;
import x.util.StringMap;

public interface TaggedValueStringMap
    extends StringMap, TaggedStringSources
{
    TaggedValue newValue();
    TaggedValue[] getValuesFor(PageTags tags);
}
