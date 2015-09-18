package se.uilist;

import x.event.LiveList;
import x.event.XLiveList;
import x.pagefactories.CellConfigurer;
import x.uilist.IXListCell;
import x.uilist.StringToListFilter;
import x.uiwidget.XComponent;
import x.uiwidget.XSearchableList;

import javax.swing.*;
import java.util.ArrayList;

final class SESearchableListBuilder
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
        return null;
    }

    @Override
    public XSearchableList.Builder configurer(IXListCell.ConfigProducer configurer) {
        return null;
    }

    @Override
    public XSearchableList build() {
        SESearchableList list = SESearchableList.of(items,new JLabel(),new CellConfigurer());
        SESearchFilterInstaller.seSpecificInstall(list, StringToListFilter.DEFAULT);
        return list;
    }
}
