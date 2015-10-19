package se.uilist;


import c1.uilist.C1SearchableList;
import x.app.Registry;
import x.event.SwappableList;
import x.uilist.StringToList;
import x.uilist.XListContentInstaller;
import x.uiwidget.XSearchableList;
import x.util.Runner;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SEListContentInstaller
    implements XListContentInstaller
{

    @Override
    public void install(XSearchableList list, SwappableList items, StringToList stringToList) {
        seSpecificInstall((SESearchableList) list,items,stringToList);
    }

    private void seSpecificInstall(SESearchableList list, final SwappableList items, final StringToList stringToList) {
        final JTextField search = list.searchTerm;
        search.addKeyListener(new KeyListener() {
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
            @Override public void keyTyped(KeyEvent e) {
                runner().invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        items.become(stringToList.listFor(search.getText()));
                    }
                });
            }
        });
    }

    private static Runner runner() {
        return Registry.get(Runner.class);
    }
}
