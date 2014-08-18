package oc1.screen;

/**
 *
 * @author Curt
 */
public abstract class ScreenController {

    public interface Lookup {
        ScreenController lookup(ScreenLink link);
    }
    
    public ScreenContext getContext() {
        ScreenContext context = new ScreenContext();
        addSpecifics(context);
        return context;
    }
    
    public abstract void addSpecifics(ScreenContext context);
}
