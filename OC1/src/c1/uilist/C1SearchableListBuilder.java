package c1.uilist;

import com.codename1.ui.Label;
import x.event.LiveList;
import x.event.XLiveList;
import x.pagefactories.CellConfigurer;
import x.uilist.IXListCell;
import x.uilist.StringToListFilter;
import x.uiwidget.XComponent;
import x.uiwidget.XSearchableList;

import java.util.ArrayList;

final class C1SearchableListBuilder
        implements XSearchableList.Builder
{
    LiveList items = XLiveList.of(new ArrayList());

    @Override
    public XSearchableList.Builder items(LiveList items) {
        this.items = items;
        return this;
    }

    @Override
    public XSearchableList.Builder action(XComponent action) {
        return this;
    }

    @Override
    public XSearchableList.Builder configurer(IXListCell.ConfigProducer configurer) {
        return this;
    }

    @Override
    public XSearchableList build() {
        C1SearchableList list = C1SearchableList.of(items,new Label(),new CellConfigurer());
        C1SearchFilterInstaller.c1SpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }
}
