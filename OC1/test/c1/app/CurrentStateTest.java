package c1.app;

import x.services.XLocationProvider;
import com.codename1.io.Storage;
import config.ShouldRun;
import x.app.Registry;
import x.app.CurrentState;
import fake.FakeLocationProvider;
import fake.FakeStorage;
import x.event.Change;
import x.services.XLocations;
import x.services.XServiceProviders;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.Test;

public class CurrentStateTest {
    
    FakeListener listener = new FakeListener(); 
    FakeListener listener2 = new FakeListener(); 
    CurrentState testObject;
    
    static class FakeListener implements Change.Listener {
        boolean called;
        public void onChange() {
            called = true;
        }
    }
    
    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
        Registry.put(Storage.class, new FakeStorage());
        Registry.put(XLocationProvider.class, new FakeLocationProvider());
        Registry.put(XLocations.class, new XLocations());
        Registry.put(XServiceProviders.class, new XServiceProviders());
        testObject = new CurrentState();
    }

    @Test
    public void is_not_null() {
        assertNotNull(testObject);
    }

    @Test
    public void notifies_listener_when_there_is_a_change() {
        testObject.addListener(listener);
        testObject.broadcastChange();
        
        assertTrue(listener.called);
    }

    @Test
    public void notifies_listeners_when_there_is_a_change() {
        testObject.addListener(listener);
        testObject.addListener(listener2);
        
        testObject.broadcastChange();
        
        assertTrue(listener.called);
        assertTrue(listener2.called);
    }
    
}
