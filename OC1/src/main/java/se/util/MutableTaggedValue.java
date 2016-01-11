package se.util;

import x.page.PageTags;

public interface MutableTaggedValue
    extends TaggedValue
{
    void setTags(PageTags tags);
    void setContents(String value);
}
