package oc1.screen;

/**
 *
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
