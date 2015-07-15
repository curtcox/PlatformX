package x.stores;

import x.Registry;
import x.domain.ID;
import x.domain.Rating;

import java.util.HashMap;
import java.util.Map;

/**
 * The ratings that I have given.
 */
public final class MyRatings {

    private final Map<ID,Rating> ratings = new HashMap();
    private final MapStorageIO io;
    private static final String MY_RATINGS = "my_ratings";

    public static MyRatings of() {
        return Registry.get(MyRatings.class);
    }
    
    public MyRatings() {
        io = new MapStorageIO(getStorage(),new MyRatingsIO(),MY_RATINGS);
        loadRatingsFromStorage();    
    }

    private XStorage getStorage() {
        return Registry.get(XStorage.class);
    }
    
    public Rating getFor(ID id) {
        if (ratings.containsKey(id)) {
            return ratings.get(id);
        }
        return new Rating("");
    }

    public void put(ID id, Rating rating) {
        ratings.put(id, rating);
        saveRatingsToStorage();
    }

    private void loadRatingsFromStorage() {
        ratings.putAll(io.readMap());
    }

    private void saveRatingsToStorage() {
        io.writeMap(ratings);
    }

}
