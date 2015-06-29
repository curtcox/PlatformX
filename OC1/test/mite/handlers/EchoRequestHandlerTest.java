package mite.handlers;

import mite.RequestHandler;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EchoRequestHandlerTest {

    @Test
    public void can_create() {
        assertNotNull(EchoRequestHandler.of());
    }

    @Test
    public void is_RequestHandler() {
        assertTrue(EchoRequestHandler.of() instanceof RequestHandler);
    }

}