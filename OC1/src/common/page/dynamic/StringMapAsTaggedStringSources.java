package common.page.dynamic;

import common.event.StringSource;
import common.page.PageTags;
import common.util.StringMap;

public class StringMapAsTaggedStringSources
    implements TaggedStringSources
{
    public StringMapAsTaggedStringSources(StringMap stringMap) {

    }

    @Override
    public StringSource[] get(PageTags tags) {
        return new StringSource[0];
    }
}
