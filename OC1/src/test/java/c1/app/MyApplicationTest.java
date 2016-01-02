package c1.app;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assume.assumeTrue;

public class MyApplicationTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
    }

    @Test
    public void can_create() {
        new MyApplication();
    }

}