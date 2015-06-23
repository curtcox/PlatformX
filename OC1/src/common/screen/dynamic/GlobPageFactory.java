package common.screen.dynamic;

import common.page.Page;
import common.screen.PageFactory;
import common.page.PageLink;
import common.util.Glob;

public final class GlobPageFactory
    implements PageFactory
{
    final Glob glob;
    final PageFactory inner;

    private GlobPageFactory(String glob, PageFactory inner) {
        this.glob = Glob.of(glob);
        this.inner = inner;
    }

    public static PageFactory filter(String glob, PageFactory inner) {
        return new GlobPageFactory(glob,inner);
    }

    public final Page[] create(PageLink link) {
        if (glob.matches(link.tags)) {
            return inner.create(link);
        }
        return new Page[0];
    }

}
