package oc1;

import oc1.domain.ServiceProvider;
import oc1.event.Change;
import oc1.stores.ServiceProviders;
import java.util.ArrayList;
import java.util.List;

/**
 * The current state of the application.
 * @author Curt
 */
public final class CurrentState
    implements Change.Source
{
    private final List<Change.Listener> listeners = new ArrayList<Change.Listener>();
    
    public static final CurrentState get() {
        return Registry.get(CurrentState.class);
    }

    public void addListener(Change.Listener listener) {
        listeners.add(listener);
    }

    public void broadcastChange() {
        for (Change.Listener listener : listeners) {
            listener.onChange();
        }
    }

//    public ServiceProvider selected = firstProviderOrNull();
//    private ServiceProvider firstProviderOrNull() {
//        ServiceProviders serviceProviders = Registry.get(ServiceProviders.class);
//        if (serviceProviders.all().size()<1) {
//            return null;
//        }
//        return serviceProviders.all().get(0);
//    }
}
