package se.uilist;

import common.uilist.ISearchFilterInstaller;
import common.uilist.StringToListFilter;
import common.uiwidget.ISearchableList;

import javax.swing.*;
import java.awt.*;
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
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        applyCurrentFilter(list, stringToListFilter, search.getText());
                    }
                });
            }
        });

    }

    private static void applyCurrentFilter(SESearchableList list, StringToListFilter stringToListFilter,String text) {
        setFilterText(list.filterListModel,stringToListFilter,text);
    }

    static void setFilterText(SEFilterListModel model, StringToListFilter stringToListFilter, String text) {
        model.setFilter(stringToListFilter.listFilterFor(text));
        model.dataChanged();
    }

    @Override
    public void install(ISearchableList list, StringToListFilter stringToListFilter) {
        seSpecificInstall((SESearchableList) list, stringToListFilter);
    }
}
