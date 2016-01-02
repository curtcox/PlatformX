package se.uilist;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import x.uilist.IXListCell;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class SEBasicListCellRendererTest {

    MyConfigProducer configurer = new MyConfigProducer();
    IXListCell.Config config = new IXListCell.Config("?","??","???");
    SEBasicListCellRenderer testObject = new SEBasicListCellRenderer(configurer);
    JList list = null;
    Object value = new Object();
    int index = 0;
    boolean selected;
    boolean hasFocus;

    class MyConfigProducer implements IXListCell.ConfigProducer {
        Object value;

        @Override
        public IXListCell.Config configFor(Object value) {
            this.value = value;
            return config;
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
    public void getListCellRendererComponent_uses_given_ConfigProducer() {
        getListCellRendererComponent();
        assertSame(value,configurer.value);
    }

    @Test
    public void getListCellRendererComponent_configures_returned_ListCell() {
        SEListCell cell = getListCellRendererComponent();
        assertSame(config.first,cell.firstRow.getText());
        assertSame(config.second,cell.secondRow.getText());
    }

    SEListCell getListCellRendererComponent() {
        return testObject.getListCellRendererComponent(list,value,index,selected,hasFocus);
    }
}