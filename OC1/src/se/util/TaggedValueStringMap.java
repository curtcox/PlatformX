package se.util;

import common.page.ScreenTags;
import common.screen.dynamic.TaggedStringSources;
import common.util.StringMap;

public interface TaggedValueStringMap
    extends StringMap, TaggedStringSources
{
    TaggedValue newValue();
    TaggedValue[] getValuesFor(ScreenTags tags);
}
