package se.uilist;

import common.uilist.IListCell;
import common.uilist.ListCellConfigurer;
import junit.framework.TestCase;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SEBasicListCellRendererTest {

    MyListCellConfigurer configurer = new MyListCellConfigurer();
    SEBasicListCellRenderer testObject = new SEBasicListCellRenderer(configurer);
    JList list = null;
    Object value = new Object();
    int index = 0;
    boolean selected;
    boolean hasFocus;

    static class MyListCellConfigurer implements ListCellConfigurer {
        IListCell button;
        Object value;

        @Override
        public void configureButton(IListCell button, Object value) {
            this.button = button;
            this.value = value;
        }
    };

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void getListCellRendererComponent_returns_a_SEListCell() {
        assertTrue(getListCellRendererComponent() instanceof SEListCell);
    }

    @Test
    public void getListCellRendererComponent_configures_returned_ListCell() {
        SEListCell cell = getListCellRendererComponent();
        assertSame(cell,configurer.button);
        assertSame(value,configurer.value);
    }

    SEListCell getListCellRendererComponent() {
        return testObject.getListCellRendererComponent(list,value,index,selected,hasFocus);
    }
}