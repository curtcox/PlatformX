package common.screen.dynamic;

import common.screen.ScreenLink;

public interface ScreenLayoutLookup {
    ScreenLayoutProvider lookup(ScreenLink link);
}
