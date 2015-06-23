package se.util;

import common.page.ScreenTags;

public interface TaggedValue {
    void setTags(ScreenTags tags);
    ScreenTags getTags();
    void setContents(String value);
    String getContents();
}
