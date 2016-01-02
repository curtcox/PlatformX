package c1.uilist;

import com.codename1.ui.Component;
import config.ShouldRun;
import fake.FakeUIManager;
import org.junit.Before;
import org.junit.Test;
import x.event.XLiveList;
import x.uiwidget.XSearchableList;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class C1SearchableListBuilderTest {
    C1SearchableListBuilder builder = new C1SearchableListBuilder();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
        FakeUIManager.of();
    }

    @Test
    public void is_an_XSearchableListBuilder() {
        assertTrue(new C1SearchableListBuilder() instanceof XSearchableList.Builder);
    }

    @Test
    public void build_returns_an_XSearchableList() {
        assertTrue(builder.build() instanceof XSearchableList);
    }

    @Test
    public void build_returns_a_list_with_a_Component() {
        assertTrue(builder.build().getComponent() instanceof Component);
    }

    @Test
    public void build_returns_an_C1SearchableList() {
        assertTrue(builder.build() instanceof C1SearchableList);
    }

    @Test
    public void build_returns_an_C1SearchableList_when_null_action_is_specified() {
        assertTrue(builder.action(null).build() instanceof C1SearchableList);
    }

    @Test
    public void build_returns_an_C1SearchableList_when_null_configurer_is_specified() {
        assertTrue(builder.configurer(null).build() instanceof C1SearchableList);
    }

    @Test
    public void build_returns_an_empty_list_by_default() {
        C1SearchableList list = (C1SearchableList) builder.build();
        assertEquals(0, list.filterListModel.getSize());
    }

    @Test
    public void build_returns_a_one_item_list_when_built_with_one() {
        XLiveList list = XLiveList.of(Arrays.asList(""));
        C1SearchableList searchable = (C1SearchableList) builder.items(list).build();
        assertEquals(1,searchable.filterListModel.getSize());
    }

}