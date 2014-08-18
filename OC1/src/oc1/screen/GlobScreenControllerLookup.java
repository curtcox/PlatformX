package oc1.screen;

import java.util.ArrayList;
import java.util.List;
import oc1.util.Glob;

/**
 *
 * @author Curt
 */
final class GlobScreenControllerLookup
    implements ScreenController.Lookup
{
    final List<Glob> globs = new ArrayList();
    final List<ScreenController> controllers = new ArrayList();

    void add(Glob glob, ScreenController controller) {
        globs.add(glob);
        controllers.add(controller);
    }

    public ScreenController lookup(ScreenLink link) {
        for (int i=0; i<globs.size(); i++) {
            Glob glob = globs.get(i);
            if (glob.matches(link.screen)) {
                return controllers.get(i);
            }
        }
        return null;
    }
    
}
