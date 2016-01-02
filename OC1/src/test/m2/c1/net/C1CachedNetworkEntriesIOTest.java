package c1.net;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import x.util.Strings;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class C1CachedNetworkEntriesIOTest {
    
    CachedNetworkEntriesIO testObject = new CachedNetworkEntriesIO();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
    }

    @Test
    public void written_form_contains_key_and_value() throws URISyntaxException {
        URI key = new URI("http://example.com/whatever");
        NetworkCacheEntry value = NetworkCacheEntry.newEntryFor(key);
    
        String written = testObject.writePair(key, value);
        
        assertTrue(Strings.contains(written, key.toString()));
        assertTrue(Strings.contains(written,value.toString()));
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
