package se.event;

import org.junit.Test;
import x.event.SwappableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SESwappableListFactoryTest {

    @Test
    public void is_a_SwappableListFactory() {
        assertTrue(new SESwappableListFactory() instanceof SwappableList.Factory);
    }

    @Test
    public void from_returns_a_SwappableList() {
        SESwappableListFactory factory = new SESwappableListFactory();
        assertTrue(factory.from(new ArrayList()) instanceof SwappableList);
    }

    @Test
    public void from_retruns_a_list_with_same_elements_given() {
        SESwappableListFactory factory = new SESwappableListFactory();
        List list = Arrays.asList(this,"red",42);
        SwappableList swappable = factory.from(list);
        assertEquals(list.size(),swappable.size());
        for (int i=0; i<list.size(); i++) {
            assertSame(list.get(i),swappable.get(i));
        }
    }
}