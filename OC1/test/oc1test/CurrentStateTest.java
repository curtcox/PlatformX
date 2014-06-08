package oc1test;

import oc1.Registry;
import oc1.CurrentState;
import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import fake.FakeLocationManager;
import fake.FakeStorage;
import oc1.event.Change;
import oc1.services.Locations;
import oc1.stores.ServiceProviders;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Curt
 */
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
        Registry.put(Storage.class, new FakeStorage());
        Registry.put(LocationManager.class, new FakeLocationManager());
        Registry.put(Locations.class, new Locations());
        Registry.put(ServiceProviders.class, new ServiceProviders());
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
