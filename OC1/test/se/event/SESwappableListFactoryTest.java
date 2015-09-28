package se.event;

import org.junit.Test;
import x.event.SwappableList;

import java.util.ArrayList;

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
}