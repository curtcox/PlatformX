package common.screen.dynamic;

import java.util.ArrayList;
import java.util.List;

import common.screen.ScreenLink;
import common.util.Glob;

final class GlobScreenLayoutLookup
    implements ScreenLayoutLookup
{
    final List<Glob> globs = new ArrayList();
    final List<ScreenLayoutProvider> layouts = new ArrayList();

    void add(Glob glob, ScreenLayoutProvider layout) {
        globs.add(glob);
        layouts.add(layout);
    }

    public ScreenLayoutProvider lookup(ScreenLink link) {
        for (int i=0; i<globs.size(); i++) {
            Glob glob = globs.get(i);
            if (glob.matches(link.tags)) {
                return layouts.get(i);
            }
        }
        return null;
    }
}
