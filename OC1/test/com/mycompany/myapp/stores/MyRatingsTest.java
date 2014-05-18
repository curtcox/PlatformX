package com.mycompany.myapp.stores;

import com.codename1.io.Storage;
import com.mycompany.fake.FakeStorage;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.domain.ID;
import com.mycompany.myapp.domain.Rating;
import java.io.ByteArrayInputStream;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class MyRatingsTest {
    
    FakeStorage storage = new FakeStorage();
    MyRatings ratings1;
    
    @Before
    public void setUp() {
        Registry.put(Storage.class, storage);
        ratings1 = new MyRatings();
    }
    
    @Test
    public void test_getFor_returns_value_previously_put_in_storage() {
        ID id = new ID("" + hashCode());
        Rating rating = new Rating(toString());
        ratings1.put(id, rating);
        storage.inputStream = new ByteArrayInputStream(storage.outputStream.toByteArray());
        
        MyRatings ratings2 = new MyRatings();
        Rating actual = ratings2.getFor(id);
        
        assertEquals(rating,actual);
    }
    
}
