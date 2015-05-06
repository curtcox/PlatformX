package common.screen.dynamic;

import common.event.StringSource;
import common.screen.ScreenTags;
import common.util.StringMap;

public class StringMapAsTaggedStringSources
    implements TaggedStringSources
{
    public StringMapAsTaggedStringSources(StringMap stringMap) {

    }

    @Override
    public StringSource[] get(ScreenTags tags) {
        return new StringSource[0];
    }
}
