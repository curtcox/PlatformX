package oc1.util;

import java.util.HashMap;
import java.util.Map;
import oc2.screens.HomeScreenController;
import oc2.screens.HomeScreenControllerMirror;
import oc2.screens.HowToScreenController;
import oc2.screens.HowToScreenControllerMirror;
import oc2.screens.RateScreenController;
import oc2.screens.RateScreenControllerMirror;

/**
 * For finding the Mirror for a given object.
 * @author Curt
 */
public final class Mirrors {
    
    private static final Map<Class,Mirror> mirrors = new HashMap<Class,Mirror>(){{
        put(HomeScreenController.class, new HomeScreenControllerMirror());
        put(RateScreenController.class,new RateScreenControllerMirror());
        put(HowToScreenController.class, new HowToScreenControllerMirror());
    }};
    
    public static Mirror of(Object o) {
        Mirror mirror = mirrors.get(o.getClass());
        mirror.setTarget(o);
        return mirror;
    }
}
