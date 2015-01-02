package common.stores;

import com.codename1.io.Storage;
import fake.FakeC1RegistryLoader;
import fake.FakeStorage;
import common.Registry;
import common.domain.ID;
import common.domain.Rating;
import java.io.ByteArrayInputStream;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class MyRatingsTest {
    
    MyRatings ratings1;
    
    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
        ratings1 = new MyRatings();
    }
    
    @Test
    public void test_getFor_returns_value_previously_put_in_storage() {
        ID id = new ID("" + hashCode());
        Rating rating = new Rating(toString());
        ratings1.put(id, rating);
        FakeStorage storage = (FakeStorage) Registry.get(Storage.class);
        storage.inputStream = new ByteArrayInputStream(storage.outputStream.toByteArray());
        
        MyRatings ratings2 = new MyRatings();
        Rating actual = ratings2.getFor(id);
        
        assertEquals(rating,actual);
    }
    
}
