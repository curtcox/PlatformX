package oc2.screens;

import java.util.Arrays;
import oc1.util.Mirror;

public final class HomeScreenControllerMirror
    implements Mirror
{
    HomeScreenController target;
    
    public void setTarget(Object target) {
        this.target = (HomeScreenController) target;
    }

    public Object invoke(String method, Object[] args) {
        if (method.equals("there_is_a_selected_provider")) { return target.there_is_a_selected_provider(); }
        if (method.equals("provider_details")) { return target.provider_details(); }
        if (method.equals("provider_rating")) { return target.provider_rating(); }
        if (method.equals("search_nearby")) { return target.searchNearbyButton();}
        throw new IllegalArgumentException(method + Arrays.asList(args));
    }

    public String[] getMethods() {
        return new String[] {"there_is_a_selected_provider","provider_details","provider_rating","search_nearby"};
    }
}
