package tests;

import com.codename1.testing.AbstractTest;

public class MainScreenTest extends AbstractTest {
    
    public boolean runTest() throws Exception {
        assertTitle();
        assertLabels();
        return true;
    }

    private void assertTitle() {
        assertTitle("Oyster Cracker");
    }

    private void assertLabels() throws Exception {
        assertLabel("Rate");
        assertLabel("Search");
        assertLabel("Profile");
        assertLabel("How To");
    }
}
