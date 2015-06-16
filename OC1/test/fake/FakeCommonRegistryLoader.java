package fake;

import common.Registry;
import common.log.CommonLogManager;
import common.log.CommonLogWriter;
import common.log.ILogManager;
import common.ui.IDisplay;

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
