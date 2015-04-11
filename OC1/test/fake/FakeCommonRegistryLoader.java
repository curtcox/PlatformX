package fake;

import common.Registry;
import common.domain.ServiceProvider;
import common.log.ILogManager;
import common.ui.IDisplay;
import common.ui.IFormFactory;
import se.log.LogManager;
import se.log.LogWriter;

public class FakeCommonRegistryLoader {
    
    public static void load() {
        put(ILogManager.class,      new LogManager());
        put(LogWriter.class,        new LogWriter());
        put(IDisplay.class,         new FakeDisplay());
        put(IFormFactory.class,     new FakeFormFactory());
    }
    
    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
