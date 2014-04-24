package com.mycompany.myapp.domain;

import com.mycompany.myapp.stores.ServiceProviders;

/**
 *
 * @author Curt
 */
public final class CurrentState {
    
    private final ServiceProviders serviceProviders = ServiceProviders.of();
    
    public ServiceProvider selected = serviceProviders.all().get(0);
    
    private static final CurrentState state = new CurrentState();
    
    public static final CurrentState get() {
        return state;
    }
}
