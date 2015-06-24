package se.util;

import x.page.PageTags;

public interface TaggedValue {
    void setTags(PageTags tags);
    PageTags getTags();
    void setContents(String value);
    String getContents();
}
