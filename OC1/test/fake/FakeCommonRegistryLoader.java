package fake;

import x.Registry;
import x.log.CommonLogManager;
import x.log.CommonLogWriter;
import x.log.ILogManager;
import x.ui.IDisplay;

public class FakeCommonRegistryLoader {
    
    public static void load() {
        put(ILogManager.class,      new CommonLogManager());
        put(CommonLogWriter.class,  new CommonLogWriter());
        put(IDisplay.class,         new FakeDisplay());
    }
    
    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
