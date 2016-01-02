package fake;

import an.a22.app.AnApplication;
import an.a22.pagefactories.AnItemListPageFactoryFactory;
import an.a22.ui.AnFormFactory;
import android.app.Activity;
import android.content.Context;
import org.robolectric.Robolectric;
import x.app.Registry;
import x.app.CurrentState;
import x.domain.ConsumerServiceProvider;
import x.log.ILogManager;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.pagefactories.ItemListPageFactoryFactory;
import x.services.XConsumerServiceProviders;
import x.ui.IDisplay;
import x.ui.IFormFactory;

public class FakeAnRegistryLoader {
    
    public static void load() {
        put(ILogManager.class,                new XLogManager());
        put(XLogWriter.class,                 new XLogWriter());
        put(ConsumerServiceProvider.class,            ConsumerServiceProvider.NULL);
        put(XConsumerServiceProviders.class,           new XConsumerServiceProviders());
        put(CurrentState.class,               new CurrentState());
        put(IFormFactory.class,               new AnFormFactory());
        put(IDisplay.class,                   new FakeDisplay());
        AnApplication activity = Robolectric.setupActivity(AnApplication.class);
        put(Context.class,                    activity);
        put(Activity.class,                   activity);
        put(ItemListPageFactoryFactory.class, new AnItemListPageFactoryFactory());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
