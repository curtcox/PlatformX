package se.uilist;

import config.ShouldRun;
import x.event.Change;
import x.event.LiveList;
import x.uilist.*;
import mach.Mocks;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import x.event.XLiveList;

import javax.swing.*;
import javax.swing.event.ListDataListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class SESearchFilterInstallerTest {

    Change.Listener listener;
    ListDataListener listDataListener;
    LiveList items = XLiveList.of(new ArrayList());
    JComponent action =  new JLabel();
    IXListCell.ConfigProducer configurer;
    SESearchableList searchableList = SESearchableList.of(items,action,configurer);
    StringToListFilter stringToListFilter = StringToListFilter.DEFAULT;
    SESearchFilterInstaller testObject = new SESearchFilterInstaller();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
        Mocks.init(this);
        items.addListener(listener);
    }

    @Test
    public void can_create() {
        assertNotNull(new SESearchFilterInstaller());
    }

    @Test
    public void install_does_not_change_list_size() {
        items.add("1");
        assertEquals(1, searchableList.filterListModel.getSize());
        testObject.install(searchableList, stringToListFilter);
        assertEquals(1, searchableList.filterListModel.getSize());
    }

    @Test
    public void setFilterText_changes_list_size() throws InvocationTargetException, InterruptedException {
        items.add("1");
        SEFilterListModel model = SEFilterListModel.of(items);
        model.addListDataListener(listDataListener);
        SESearchFilterInstaller.setFilterText(model, stringToListFilter, "Q");
        assertEquals(0, model.getSize());
    }

    @Test @Ignore
    public void keyPress_changes_list_size() throws InvocationTargetException, InterruptedException {
        Frame frame = new Frame();
        TextField text = new TextField();
        frame.add(text);
        frame.pack();
        frame.setVisible(true);
        items.add("1");
        testObject.install(searchableList, stringToListFilter);
        simulateKeyPress();
        assertEquals(0, searchableList.filterListModel.getSize());
    }

    void simulateKeyPress() {
        JTextField searchTerm = searchableList.searchTerm;
        int modifiers = 0;
        char keyChar = 'Z';
        searchTerm.dispatchEvent(new KeyEvent(searchTerm,
                KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                modifiers, KeyEvent.VK_UNDEFINED, keyChar));
    }
}