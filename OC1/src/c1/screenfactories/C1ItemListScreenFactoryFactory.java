package c1.screenfactories;

import common.screen.PageFactory;
import common.screenfactories.ItemListScreenFactoryFactory;

import java.util.List;

public final class C1ItemListScreenFactoryFactory
    implements ItemListScreenFactoryFactory
{
    @Override
    public PageFactory newFactory(List values) {
        return new C1ItemListPageFactory(values);
    }
}
