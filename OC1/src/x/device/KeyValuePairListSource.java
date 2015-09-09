package x.device;

import x.event.LiveList;
import x.pagefactories.KeyValuePair;

public interface KeyValuePairListSource {
    LiveList<KeyValuePair> asKeyValuePairs();
}
