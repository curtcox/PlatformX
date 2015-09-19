package se.uilist;

import x.app.Registry;
import x.uilist.ISearchFilterInstaller;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;
import x.util.Runner;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

final class SESearchFilterInstaller
    implements ISearchFilterInstaller
{
    
    static void seSpecificInstall(final SESearchableList list, final StringToListFilter stringToListFilter) {
        final JTextField search = list.searchTerm;
        search.addKeyListener(new KeyListener() {
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
            @Override public void keyTyped(KeyEvent e) {
                runner().invokeLater(new Runnable() {
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
    public void install(XSearchableList list, StringToListFilter stringToListFilter) {
        seSpecificInstall((SESearchableList) list, stringToListFilter);
    }

    private static Runner runner() {
        return Registry.get(Runner.class);
    }
}
