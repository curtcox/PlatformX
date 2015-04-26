package se.util;

import common.event.StringSource;
import common.screen.ScreenTags;

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
        for (final TaggedValue value : taggedValues) {
            if (value.getTags().equals(tags)) {
                return new StringSource[] {
                    new StringSource() {
                        @Override
                        public String getString() {
                            return value.getContents();
                        }
                    }
                };
            }
        }
        return new StringSource[0];
    }
}
