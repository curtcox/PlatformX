package common.pages;

import common.util.AbstractMirror;

public final class HomeMirror
    extends AbstractMirror<Home>
{
    public HomeMirror() {
        super(Home.class,"there_is_a_selected_provider","provider_details","provider_rating","search_nearby");    
    }
    
    public Object invoke(String method, Object[] args) {
        if (method.equals("there_is_a_selected_provider")) { return target.there_is_a_selected_provider(); }
        if (method.equals("provider_details")) { return target.provider_details(); }
        if (method.equals("provider_rating")) { return target.provider_rating(); }
        if (method.equals("search_nearby")) { return target.searchNearbyButton();}
        throw methodNotFound(method,args);
    }
}
