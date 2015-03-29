package se.util;

import common.screen.ScreenTags;

public final class SimpleTaggedValue
    implements TaggedValue
{
    private ScreenTags tags;

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

    }

    @Override
    public String getContents() {
        return null;
    }
}
