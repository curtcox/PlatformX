package x.event;

import x.util.NamedValue;

public interface NamedValueListSource {
    LiveList<NamedValue> asNamedValues();
}
