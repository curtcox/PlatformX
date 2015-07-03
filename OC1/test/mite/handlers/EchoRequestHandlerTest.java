package mite.handlers;

import config.ShouldRun;
import mite.RequestHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class EchoRequestHandlerTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Mite);
    }

    @Test
    public void can_create() {
        assertNotNull(EchoRequestHandler.of());
    }

    @Test
    public void is_RequestHandler() {
        assertTrue(EchoRequestHandler.of() instanceof RequestHandler);
    }

}