package fake;

import common.Registry;
import common.domain.ServiceProvider;
import common.log.CommonLogManager;
import common.log.CommonLogWriter;
import common.log.ILogManager;
import common.ui.IDisplay;
import common.ui.IFormFactory;

public class FakeSERegistryLoader {
    
    public static void load() {
        put(ILogManager.class,      new CommonLogManager());
        put(CommonLogWriter.class,  new CommonLogWriter());
        put(ServiceProvider.class,  ServiceProvider.NULL);
        put(IDisplay.class,         new FakeDisplay());
        put(IFormFactory.class,     new FakeFormFactory());
    }
    
    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
