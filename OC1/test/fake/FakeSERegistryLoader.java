package fake;

import common.Registry;
import common.domain.ServiceProvider;
import common.log.ILogManager;
import common.ui.IDisplay;
import se.log.LogManager;
import se.log.LogWriter;

public class FakeSERegistryLoader {
    
    public static void load() {
        put(ILogManager.class,      new LogManager());
        put(LogWriter.class,        new LogWriter());
        put(ServiceProvider.class,  ServiceProvider.NULL);
        put(IDisplay.class,         new FakeDisplay());
    }
    
    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
