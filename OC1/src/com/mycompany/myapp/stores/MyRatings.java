package com.mycompany.myapp.stores;

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
    
    public static MyRatings of() {
        return Registry.get(MyRatings.class);
    }
    
    public Rating getFor(ID id) {
        if (ratings.containsKey(id)) {
            return ratings.get(id);
        }
        return new Rating("");
    }

    public void put(ID id, Rating rating) {
        ratings.put(id, rating);
    }
    
}
