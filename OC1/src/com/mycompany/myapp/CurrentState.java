package com.mycompany.myapp;

import com.mycompany.myapp.domain.ServiceProvider;
import com.mycompany.myapp.event.Change;
import com.mycompany.myapp.stores.ServiceProviders;

/**
 * The current state of the application.
 * @author Curt
 */
public final class CurrentState
    implements Change.Source
{
    private Change.Listener listener;
    public ServiceProvider selected = firstProviderOrNull();
    
    public static final CurrentState get() {
        return Registry.get(CurrentState.class);
    }

    public void addListener(Change.Listener listener) {
        this.listener = listener;
    }

    public void broadcastChange() {
        listener.onChange();
    }

    private ServiceProvider firstProviderOrNull() {
        ServiceProviders serviceProviders = Registry.get(ServiceProviders.class);
        if (serviceProviders.all().size()<1) {
            return null;
        }
        return serviceProviders.all().get(0);
    }
}
