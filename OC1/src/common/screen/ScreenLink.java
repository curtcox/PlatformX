package common.screen;

import java.util.Arrays;

/**
 * A link to a screen.  This is analogous to an HTML link.
 * @author Curt
 */
public final class ScreenLink {

    public static ScreenLink of(String name, Object... args) {
        return new ScreenLink(name,args);
    }

    public interface Factory {
        ScreenLink create();    
    }
    
    public final String screen;
    public final Object[] args;
    
    private ScreenLink(String screen, Object... args) {
        this.screen = screen;
        this.args = args;
    }
    
    @Override
    public String toString() {
        return screen + " " + Arrays.asList(args);
    }
}
