package fake;

//import com.codename1.impl.ImplementationFactory;
import com.codename1.ui.Display;
import com.codename1.ui.plaf.UIManager;

public class FakeUIManager {

    private FakeUIManager() {}
    
    static {
        init();
    }
    
    static void init() {
        Object o = new Object();
//        ImplementationFactory.setInstance(new FakeImplementationFactory());
        Display.init(o);
    }
    
    public static UIManager of() {
        return UIManager.getInstance();
    }
    
    static Display display() {
        return Display.getInstance();
    }

}
