package c1.screenfactories;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class IndexScreenFactoryTest {

    @Test
    public void can_create() {
        assertNotNull(new IndexScreenFactory(new ArrayList<String>()));
    }
}