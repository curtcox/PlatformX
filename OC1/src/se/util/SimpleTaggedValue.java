package se.util;

import common.screen.ScreenTags;

final class SimpleTaggedValue
    implements TaggedValue
{
    private ScreenTags tags;
    private String contents;

    @Override
    public void setTags(ScreenTags tags) {
        this.tags = tags;
    }

    @Override
    public ScreenTags getTags() {
        return tags;
    }

    @Override
    public void setContents(String value) {
        this.contents = value;
    }

    @Override
    public String getContents() {
        return contents;
    }
}
