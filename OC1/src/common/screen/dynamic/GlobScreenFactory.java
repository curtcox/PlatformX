package common.screen.dynamic;

import common.screen.Page;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.util.Glob;

public final class GlobScreenFactory
    implements ScreenFactory
{
    final Glob glob;
    final ScreenFactory inner;

    private GlobScreenFactory(String glob, ScreenFactory inner) {
        this.glob = Glob.of(glob);
        this.inner = inner;
    }

    public static ScreenFactory filter(String glob, ScreenFactory inner) {
        return new GlobScreenFactory(glob,inner);
    }

    public final Page[] create(ScreenLink link) {
        if (glob.matches(link.tags)) {
            return inner.create(link);
        }
        return new Page[0];
    }

}
