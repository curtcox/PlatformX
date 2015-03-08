package se.util;

import common.util.StringMap;
import java.util.Collection;

public interface TaggedValueStringMap
    extends StringMap
{
    public interface TaggedValue {
        void addTag(String tag);
        void removeTag(String tag);
        Collection getTags();
        void set(String value);
    }

    TaggedValue newValue();
}
