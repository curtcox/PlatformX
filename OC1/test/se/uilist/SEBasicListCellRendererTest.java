package se.uilist;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import x.uilist.IXListCell;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class SEBasicListCellRendererTest {

    MyListCellConfigurer configurer = new MyListCellConfigurer();
    SEBasicListCellRenderer testObject = new SEBasicListCellRenderer(configurer);
    JList list = null;
    Object value = new Object();
    int index = 0;
    boolean selected;
    boolean hasFocus;

    static class MyListCellConfigurer implements IXListCell.ConfigProducer {
        Object value;

        @Override
        public IXListCell.Config configFor(Object value) {
            this.value = value;
            return null;
        }
    };

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
    }

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
        assertSame(value,configurer.value);
    }

    SEListCell getListCellRendererComponent() {
        return testObject.getListCellRendererComponent(list,value,index,selected,hasFocus);
    }
}