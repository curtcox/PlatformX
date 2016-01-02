package se.uilist;

import x.uiwidget.XSearchableList;

public final class SESearchableListFactory
    implements XSearchableList.Factory
{
    @Override
    public XSearchableList.Builder builder() {
        return new SESearchableListBuilder();
    }
}
