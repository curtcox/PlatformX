package google;

import x.json.JsonMap;

interface JsonItemConstructor<T> {
    T construct(JsonMap map);
}
