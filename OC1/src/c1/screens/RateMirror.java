package c1.screens;

import common.util.AbstractMirror;

public final class RateMirror
    extends AbstractMirror<Rate>
{
    public RateMirror() {
        super(Rate.class,
            "rating", "description", "provider_details_button",
            "change_location_button", "about_rating_button", "rating_button"
        );
    }
    
    @Override
    public Object invoke(String method, Object[] args) {
        if (method.equals("rating"))                  { return target.rating; };
        if (method.equals("description"))             { return target.description; }
        if (method.equals("provider_details_button")) { return target.provider_details_button(); }
        if (method.equals("change_location_button"))  { return target.change_location_button(); } 
        if (method.equals("about_rating_button"))     { return target.about_rating_button(); }
        if (method.equals("rating_button"))           { return target.rating_button((String)args[0],(String)args[1]); }
        throw methodNotFound(method, args);
    }

}
