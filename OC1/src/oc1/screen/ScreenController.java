package oc1.screen;

/**
 *
 * @author Curt
 */
public class ScreenController {

    public ScreenContext getContext() {
        ScreenContext context = new ScreenContext();
        addSpecifics(context);
        return context;
    }
    
    public void addSpecifics(ScreenContext context) {
        
    }
}
