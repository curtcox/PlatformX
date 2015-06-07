package se.uilist;

import common.uilist.ISearchFilterInstaller;
import common.uilist.StringToListFilter;
import common.uiwidget.ISearchableList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class SESearchFilterInstaller
    implements ISearchFilterInstaller
{
    
    public static void seSpecificInstall(final SESearchableList list, final StringToListFilter stringToListFilter) {
        final JTextField search = list.searchTerm;
        search.addKeyListener(new KeyListener() {
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
            @Override public void keyTyped(KeyEvent e) {
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
