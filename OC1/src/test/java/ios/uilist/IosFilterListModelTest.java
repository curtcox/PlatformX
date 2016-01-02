package ios.uilist;

import config.ShouldRun;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.uikit.UITableViewCell;
import x.event.XLiveList;
import x.uilist.IXListCell;
import x.uilist.ListFilter;

import java.util.ArrayList;
import java.util.List;

import static mach.Mocks._;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class IosFilterListModelTest {

    XLiveList listModel = XLiveList.of(new ArrayList());
    IXListCell.ConfigProducer configurer;
    IosBasicListCellRenderer renderer;

    IosFilterListModel testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
        Mocks.init(this);
        renderer = new IosBasicListCellRenderer(configurer);
        testObject = IosFilterListModel.of(listModel,renderer);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void size_is_0_when_list_is_empty() {
        assertEquals(0, testObject.getNumberOfRowsInSection(null, 0));
    }

    @Test
    public void size_is_1_when_list_has_1_item() {
        listModel.add("stuff");
        assertEquals(1, testObject.getNumberOfRowsInSection(null, 0));
    }

    @Test
    public void getElementAt_0_returns_1st_element() {
        Object expected = new Object();
        listModel.add(expected);
        assertEquals(expected,testObject.getItem(0));
    }

    @Test
    public void size_returns_filtered_size_when_set() {
        listModel.add("stuff");
        testObject.setFilter(ListFilter.ALLOW_NONE);
        assertEquals(0, testObject.getNumberOfRowsInSection(null,0));
    }

    @Test
    public void getElementAt_0_returns_1st_element_that_passes_filter() {
        final Object expected = new Object();
        listModel.add("unexpected");
        listModel.add(expected);
        testObject.setFilter(new ListFilter() {
            @Override
            public boolean passes(Object item) {
                return item==expected;
            }
        });
        assertEquals(expected,testObject.getItem(0));
    }

    @Test
    public void getTitleForHeader_returns_empty_string() {
        assertEquals("",testObject.getTitleForHeader(null,0));
    }

    @Test
    public void getTitleForFooter_returns_empty_string() {
        assertEquals("",testObject.getTitleForFooter(null, 0));
    }

    @Test
    public void getSectionIndexTitles_returns_an_empty_list() {
        List<String> titles = testObject.getSectionIndexTitles(null);
        assertNotNull(titles);
        assertEquals(0,titles.size());
    }

    @Test
    public void getCellForRow_returns_IosListCell_when_index_path_is_in_list() {
        NSIndexPath indexPath = NSIndexPath.createWithItem(0,0);
        Object value = random();
        listModel.add(value);
        IXListCell.Config config = new IXListCell.Config("","",null);
        _(config); configurer.configFor(value);

        UITableViewCell cell = testObject.getCellForRow(null,indexPath);

        assertNotNull(cell);
        assertTrue(cell instanceof IosListCell);
    }

    @Test
    public void getCellForRow_returns_configured_cell_when_index_path_is_in_list() {
        NSIndexPath indexPath = NSIndexPath.createWithItem(0,0);
        Object value = random();
        listModel.add(value);
        String first = "1st";
        String second = "2nd";
        Object icon = null;
        IXListCell.Config config = new IXListCell.Config(first,second,icon);

        _(config); configurer.configFor(value);

        IosListCell cell = (IosListCell) testObject.getCellForRow(null,indexPath);

        assertSame(first, cell.getTextLabel().getText());
    }

    private Object random() {
        return this;
    }

}