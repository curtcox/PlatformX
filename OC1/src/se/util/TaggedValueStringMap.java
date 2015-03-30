package se.util;

import common.screen.ScreenTags;
import common.util.StringMap;

public final class TaggedValueStringMap
    implements StringMap
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

}
