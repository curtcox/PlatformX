package google;

import x.json.JsonMap;

interface IJsonResponseParser<T> {
    T construct(JsonMap map);
}
