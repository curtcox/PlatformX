package common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A StringMap that uses GlobS as keys.
 */
public final class GlobStringMap
    implements StringMap
{
    final List<Glob> globs = new ArrayList();
    final List<String> strings = new ArrayList();

    public void add(Glob glob, String string) {
        globs.add(glob);
        strings.add(string);
    }

    public String get(String string) {
        for (int i=0; i<globs.size(); i++) {
            Glob glob = globs.get(i);
            if (glob.matches(string)) {
                return strings.get(i);
            }
        }
        return null;
    }
}
