package com.mycompany.myapp;

import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.event.Change;
import com.mycompany.myapp.stores.ServiceProviders;
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
    public ServiceProvider selected = firstProviderOrNull();
    
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

    private ServiceProvider firstProviderOrNull() {
        ServiceProviders serviceProviders = Registry.get(ServiceProviders.class);
        if (serviceProviders.all().size()<1) {
            return null;
        }
        return serviceProviders.all().get(0);
    }
}
