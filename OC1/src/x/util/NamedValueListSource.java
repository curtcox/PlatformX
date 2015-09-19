package x.util;

import x.event.LiveList;

public interface NamedValueListSource {
    LiveList<NamedValue> asNamedValues();
}
