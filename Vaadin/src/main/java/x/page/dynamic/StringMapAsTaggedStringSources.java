package x.page.dynamic;

import x.event.StringSource;
import x.page.PageTags;
import x.util.StringMap;

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
