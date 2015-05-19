package common.screen.dynamic;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.util.Glob;

public final class GlobScreenFactory
    implements ScreenFactory
{
    final Glob glob;
    final ScreenFactory inner;

    public GlobScreenFactory(String glob, ScreenFactory inner) {
        this.glob = Glob.of(glob);
        this.inner = inner;
    }

    public final Screen[] create(ScreenLink link) {
        if (glob.matches(link.tags)) {
            return inner.create(link);
        }
        return new Screen[0];
    }

}
