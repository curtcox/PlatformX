package x.pagefactories;

import x.event.LiveList;

public interface NamedValueListSource {
    LiveList<NamedValue> asNamedValues();
}
