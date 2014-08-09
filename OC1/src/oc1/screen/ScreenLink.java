package oc1.screen;

/**
 *
 * @author Curt
 */
public final class ScreenLink {
    
    public final String screen;
    public final Screen previous;
    public final Object[] args;
    
    public ScreenLink(String screen, Screen previous, Object... args) {
        this.screen = screen;
        this.previous = previous;
        this.args = args;
    }
}
