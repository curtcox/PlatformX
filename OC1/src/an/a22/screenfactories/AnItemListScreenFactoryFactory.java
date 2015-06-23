package an.a22.screenfactories;

import common.screen.PageFactory;
import common.screenfactories.ItemListScreenFactoryFactory;

import java.util.List;

public final class AnItemListScreenFactoryFactory
    implements ItemListScreenFactoryFactory
{
    @Override
    public PageFactory newFactory(List values) {
        return new AnItemListPageFactory(values);
    }
}
