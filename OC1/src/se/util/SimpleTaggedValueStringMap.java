package se.util;

import common.event.StringSource;
import common.screen.ScreenTags;
import common.screen.dynamic.TaggedStringSources;
import common.util.Objects;
import common.util.StringMap;

import java.util.ArrayList;
import java.util.List;

public final class SimpleTaggedValueStringMap
    implements TaggedValueStringMap
{

    List<TaggedValue> taggedValues = new ArrayList();

    @Override
    public String get(String key) {
        for (TaggedValue value : taggedValues) {
            if (value.getTags().equals(ScreenTags.of(key))) {
                return value.getContents();
            }
        }
        return null;
    }

    public TaggedValue newValue() {
        TaggedValue value = new SimpleTaggedValue();
        taggedValues.add(value);
        return value;
    }

    public TaggedValue[] getValuesFor(ScreenTags tags) {
        return new TaggedValue[0];
    }

    @Override
    public StringSource[] get(ScreenTags tags) {
        return new StringSource[0];
    }
}
