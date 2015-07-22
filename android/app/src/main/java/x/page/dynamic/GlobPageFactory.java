package x.page.dynamic;

import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.util.Glob;

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
