package se.util;

import common.screen.ScreenTags;
import common.screen.dynamic.TaggedStringSources;
import common.util.StringMap;

public interface TaggedValueStringMap
    extends StringMap, TaggedStringSources
{
    TaggedValue[] getValuesFor(ScreenTags tags);
}
