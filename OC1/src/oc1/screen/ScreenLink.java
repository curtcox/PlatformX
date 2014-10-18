package oc1.screen;

/**
 * A link to a screen.  This is analogous to an HTML link.
 * @author Curt
 */
public final class ScreenLink {
    
    public interface Factory {
        ScreenLink create();    
    }
    
    public final String screen;
    public final Object[] args;
    
    public ScreenLink(String screen, Object... args) {
        this.screen = screen;
        this.args = args;
    }
}
