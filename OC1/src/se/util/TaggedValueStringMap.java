package se.util;

import common.event.StringSource;
import common.screen.ScreenTags;
import common.screen.dynamic.TaggedStringSources;
import common.util.StringMap;

public final class TaggedValueStringMap
    implements StringMap, TaggedStringSources
{

    SimpleTaggedValue taggedValue = new SimpleTaggedValue();

    @Override
    public String get(String key) {
        return taggedValue.getContents();
    }

    public TaggedValue newValue() {
        return taggedValue;
    }

    public TaggedValue[] getValuesFor(ScreenTags tags) {
        return null;
    }

    @Override
    public StringSource[] get(ScreenTags tags) {
        return null;
    }
}
