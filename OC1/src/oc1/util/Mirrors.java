package oc1.util;

import java.util.HashMap;
import java.util.Map;
import oc2.screens.Home;
import oc2.screens.HomeMirror;
import oc2.screens.HowTo;
import oc2.screens.HowToMirror;
import oc2.screens.Rate;
import oc2.screens.RateMirror;

/**
 * For finding the Mirror for a given object.
 * @author Curt
 */
public final class Mirrors {
    
    private static final Map<Class,Mirror> mirrors = new HashMap<Class,Mirror>(){{
        put(Home.class, new HomeMirror());
        put(Rate.class,new RateMirror());
        put(HowTo.class, new HowToMirror());
    }};
    
    public static Mirror of(Object o) {
        Mirror mirror = mirrors.get(o.getClass());
        mirror.setTarget(o);
        return mirror;
    }
}
