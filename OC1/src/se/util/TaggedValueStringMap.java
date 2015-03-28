package se.util;

import common.screen.ScreenTags;
import common.util.StringMap;
import java.util.Collection;

public class TaggedValueStringMap
    implements StringMap
{

    public interface TaggedValue {
        void setTags(ScreenTags tags);
        ScreenTags getTags();
        void setContents(String value);
        String getContents();
    }

    @Override
    public String get(String string) {
        return null;
    }

    TaggedValue newValue() {
        return null;
    }

    TaggedValue[] getValuesFor(ScreenTags tags) {
        return null;
    }
}
