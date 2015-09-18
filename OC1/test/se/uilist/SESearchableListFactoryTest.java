package se.uilist;

import org.junit.Test;
import x.uiwidget.XSearchableList;

import static org.junit.Assert.assertTrue;

public class SESearchableListFactoryTest {

    @Test
    public void factory_is_an_XSearchableListFactory() {
        assertTrue(new SESearchableListFactory() instanceof XSearchableList.Factory);
    }

}