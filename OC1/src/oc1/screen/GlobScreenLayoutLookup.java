package oc1.screen;

import java.util.ArrayList;
import java.util.List;
import oc1.util.Glob;

/**
 *
 * @author Curt
 */
final class GlobScreenLayoutLookup 
    implements ScreenLayout.Lookup
{
    final List<Glob> globs = new ArrayList();
    final List<ScreenLayout.Provider> layouts = new ArrayList();

    void add(Glob glob, ScreenLayout.Provider layout) {
        globs.add(glob);
        layouts.add(layout);
    }

    public ScreenLayout.Provider lookup(ScreenLink link) {
        for (int i=0; i<globs.size(); i++) {
            Glob glob = globs.get(i);
            if (glob.matches(link.screen)) {
                return layouts.get(i);
            }
        }
        return null;
    }
}
