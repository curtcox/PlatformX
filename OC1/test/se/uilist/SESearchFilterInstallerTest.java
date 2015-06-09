package se.uilist;

import common.event.LiveList;
import common.uilist.ListCellConfigurer;
import common.uilist.ListFilter;
import common.uilist.StringToListFilter;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import se.event.SELiveList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SESearchFilterInstallerTest {

    List list = new ArrayList();
    LiveList items = new SELiveList(list);
    JComponent action =  new JLabel();
    ListCellConfigurer configurer;
    SESearchableList searchableList = new SESearchableList(items,action,configurer);
    StringToListFilter stringToListFilter = StringToListFilter.DEFAULT;
    SESearchFilterInstaller testObject = new SESearchFilterInstaller();

    @Test
    public void can_create() {
        Frame frame = new Frame();
        TextField text = new TextField();
        frame.add(text);
        frame.pack();
        frame.setVisible(true);
        assertNotNull(new SESearchFilterInstaller());
    }

    @Test
    public void install_does_not_change_list_size() {
        list.add("1");
        assertEquals(1, searchableList.filterListModel.getSize());
        testObject.install(searchableList, stringToListFilter);
        assertEquals(1, searchableList.filterListModel.getSize());
    }

    @Test
    public void setFilterText_changes_list_size() throws InvocationTargetException, InterruptedException {
        list.add("1");
        ListModel listModel = SEVirtualListModel.of(items);
        SEFilterListModel model = new SEFilterListModel(listModel);
        SESearchFilterInstaller.setFilterText(model, stringToListFilter, "Q");
        assertEquals(0, model.getSize());
    }

    @Test @Ignore
    public void keyPress_changes_list_size() throws InvocationTargetException, InterruptedException {
        list.add("1");
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