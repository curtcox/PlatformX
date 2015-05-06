package common.screen.dynamic;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.util.Glob;

public abstract class GlobScreenFactory
    implements ScreenFactory
{
    final Glob glob;

    public GlobScreenFactory(String glob) {
        this.glob = Glob.of(glob);
    }

    public final Screen[] create(ScreenLink link) {
        if (glob.matches(link.tags)) {
            return new Screen[] {doCreate(link)};
        }
        return null;
    }

    protected abstract Screen doCreate(ScreenLink link);
}
