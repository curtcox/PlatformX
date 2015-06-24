package fake;

import x.Registry;
import x.domain.ServiceProvider;
import x.log.CommonLogManager;
import x.log.CommonLogWriter;
import x.log.ILogManager;
import x.ui.IDisplay;
import x.ui.IFormFactory;

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
