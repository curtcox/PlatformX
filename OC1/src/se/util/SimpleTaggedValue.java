package se.util;

import x.page.PageTags;

final class SimpleTaggedValue
    implements TaggedValue
{
    private PageTags tags;
    private String contents;

    @Override
    public void setTags(PageTags tags) {
        this.tags = tags;
    }

    @Override
    public PageTags getTags() {
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
