package fake;

import se.uilist.SESearchableListFactory;
import x.pagefactories.XItemListPageFactoryFactory;
import x.app.Registry;
import x.domain.ConsumerServiceProvider;
import x.log.ILogManager;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.pagefactories.ItemListPageFactoryFactory;
import x.ui.IDisplay;
import x.ui.IFormFactory;
import x.uiwidget.XSearchableList;

public class FakeSERegistryLoader {
    
    public static void load() {
        put(ILogManager.class,                new XLogManager());
        put(XLogWriter.class,                 new XLogWriter());
        put(ConsumerServiceProvider.class,            ConsumerServiceProvider.NULL);
        put(IDisplay.class,                   new FakeDisplay());
        put(IFormFactory.class,               new FakeFormFactory());
        put(ItemListPageFactoryFactory.class, new XItemListPageFactoryFactory());
        put(XSearchableList.Factory.class,    new SESearchableListFactory());
    }
    
    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
