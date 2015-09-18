package se.uilist;

import org.junit.Test;
import x.event.XLiveList;
import x.uiwidget.XSearchableList;

import javax.swing.*;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SESearchableListBuilderTest {

    SESearchableListBuilder builder = new SESearchableListBuilder();

    @Test
    public void is_an_XSearchableListBuilder() {
        assertTrue(new SESearchableListBuilder() instanceof XSearchableList.Builder);
    }

    @Test
    public void build_returns_an_XSearchableList() {
        assertTrue(builder.build() instanceof XSearchableList);
    }

    @Test
    public void build_returns_a_list_with_a_JComponent() {
        assertTrue(builder.build().getComponent() instanceof JComponent);
    }

    @Test
    public void build_returns_an_SESearchableList() {
        assertTrue(builder.build() instanceof SESearchableList);
    }

    @Test
    public void build_returns_an_empty_list_by_default() {
        SESearchableList list = (SESearchableList) builder.build();
        assertEquals(0, list.filterListModel.getSize());
    }

    @Test
    public void build_returns_a_one_item_list_when_built_with_one() {
        XLiveList list = XLiveList.of(Arrays.asList(""));
        SESearchableList searchable = (SESearchableList) builder.items(list).build();
        assertEquals(1,searchable.filterListModel.getSize());
    }

}