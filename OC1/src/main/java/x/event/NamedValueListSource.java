package x.event;

import x.util.NamedValue;

/**
 * Generally used to declare that an object is self-reflective.
 */
public interface NamedValueListSource {
    LiveList<NamedValue> asNamedValues();
}
