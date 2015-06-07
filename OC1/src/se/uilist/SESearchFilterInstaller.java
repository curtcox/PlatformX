package se.uilist;

import common.uilist.ISearchFilterInstaller;
import common.uilist.StringToListFilter;
import common.uiwidget.ISearchableList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SESearchFilterInstaller
    implements ISearchFilterInstaller
{
    
    public static void seSpecificInstall(final SESearchableList list, final StringToListFilter stringToListFilter) {
        final JTextField search = list.searchTerm;
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setFilter(list.filterListModel);
            }

            void setFilter(SEFilterListModel model) {
                model.setFilter(stringToListFilter.listFilterFor(search.getText()));
                model.dataChanged();
            }
        });        
    }

    @Override
    public void install(ISearchableList list, StringToListFilter stringToListFilter) {
        seSpecificInstall((SESearchableList) list, stringToListFilter);
    }
}
