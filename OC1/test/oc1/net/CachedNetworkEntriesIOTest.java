package oc1.net;

import oc1.net.CachedNetworkEntriesIO;
import oc1.net.NetworkCacheEntry;
import java.net.URI;
import java.net.URISyntaxException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class CachedNetworkEntriesIOTest {
    
    CachedNetworkEntriesIO testObject = new CachedNetworkEntriesIO();
    
    @Test
    public void written_form_contains_key_and_value() throws URISyntaxException {
        URI key = new URI("http://example.com/whatever");
        NetworkCacheEntry value = NetworkCacheEntry.newEntryFor(key);
    
        String written = testObject.writePair(key, value);
        
        assertTrue(written.contains(key.toString()));
        assertTrue(written.contains(value.toString()));
    }

    @Test
    public void readKey_reads_key_as_written() throws URISyntaxException {
        URI key = new URI("http://example.com/whatever");
        NetworkCacheEntry value = NetworkCacheEntry.newEntryFor(key);   
        String written = testObject.writePair(key, value);
 
        URI read = testObject.readKey(written);
        
        assertEquals(key,read);
    }

    @Test
    public void readValue_reads_value_as_written() throws URISyntaxException {
        URI key = new URI("http://example.com/whatever");
        NetworkCacheEntry value = NetworkCacheEntry.newEntryFor(key);   
        String written = testObject.writePair(key, value);
 
        NetworkCacheEntry read = testObject.readValue(written);
        
        assertEquals(value.fileName,read.fileName);
        assertEquals(value.timeStamp,read.timeStamp);
        assertEquals(value.uri,read.uri);
    }

}