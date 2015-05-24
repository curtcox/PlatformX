package common.util;

import java.util.HashMap;
import java.util.Map;
import c1.screens.*;
import common.screens.Home;
import common.screens.HomeMirror;

/**
 * For finding the Mirror for a given object.
 */
public final class Mirrors {

    private static final Map<String,Class> names = new HashMap<String,Class>();
    private static final Map<Class,Mirror> mirrors = new HashMap<Class,Mirror>();
    
    static {
        put("Home",  new Home(), new HomeMirror());
        put("Rate",  new Rate(), new RateMirror());
        put("HowTo", new HowTo(), new HowToMirror());
    };
    
    private static void put(String name, Object instance, Mirror mirror) {
        names.put(name, instance.getClass());
        mirrors.put(instance.getClass(), mirror);
        mirror.setTarget(instance);
    }
    
    public static Mirror of(Object o) {
        Mirror mirror = mirrors.get(o.getClass());
        Check.notNull(mirror,o.getClass() + " not found");
        mirror.setTarget(o);
        return mirror;
    }

    public static Mirror of(String className) {
        Class clazz = names.get(className);
        return mirrors.get(clazz);
    }

}
