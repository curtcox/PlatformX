package va.uilist;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class VaListItemTest  {

    @Test
    public void can_create() {
        assertNotNull(new VaListItem(null));
    }
}