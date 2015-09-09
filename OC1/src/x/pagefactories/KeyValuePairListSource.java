package x.pagefactories;

import x.event.LiveList;

public interface KeyValuePairListSource {
    LiveList<KeyValuePair> asKeyValuePairs();
}
