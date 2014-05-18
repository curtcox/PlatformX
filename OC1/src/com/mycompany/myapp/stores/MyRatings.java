package com.mycompany.myapp.stores;

import com.codename1.io.Storage;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.domain.ID;
import com.mycompany.myapp.domain.Rating;
import java.util.HashMap;
import java.util.Map;

/**
 * The ratings that I have given.
 * @author Curt
 */
public final class MyRatings {

    private final Map<ID,Rating> ratings = new HashMap();
    private static final String MY_RATINGS = "my_ratings";
    
    public static MyRatings of() {
        return Registry.get(MyRatings.class);
    }
    
    public MyRatings() {
        loadRatingsFromStorage();    
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
        Map<String,String> map = getMapStreamIO().readMap();
        for (Object object : map.keySet()) {
            String key = (String) object;
            String value = map.get(key);
            ratings.put(new ID(key), new Rating(value));
        }
    }

    private void saveRatingsToStorage() {
        Map<String,String> map = new HashMap();
        for (ID id : ratings.keySet()) {
            Rating rating = ratings.get(id);
            map.put(id.toString(), rating.toString());
        }
        getMapStreamIO().writeMap(map);
    }

    private MapStreamIO getMapStreamIO() {
        return new MapStreamIO(Registry.get(Storage.class),MY_RATINGS);
    }
    
}
