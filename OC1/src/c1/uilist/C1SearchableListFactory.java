package c1.uilist;

import x.uiwidget.XSearchableList;

public final class C1SearchableListFactory
    implements XSearchableList.Factory
{
    @Override
    public XSearchableList.Builder builder() {
        return new C1SearchableListBuilder();
    }
}
