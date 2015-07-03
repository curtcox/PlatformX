package x.page.dynamic;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assume.assumeTrue;

public class StringMapAsTaggedStringSourcesTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void can_create() {
        new StringMapAsTaggedStringSources(null);
    }
}