package se.util;

import common.page.PageTags;

public interface TaggedValue {
    void setTags(PageTags tags);
    PageTags getTags();
    void setContents(String value);
    String getContents();
}
