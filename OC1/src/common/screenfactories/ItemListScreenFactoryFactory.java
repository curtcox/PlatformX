package common.screenfactories;

import common.page.PageFactory;

import java.util.List;

public interface ItemListScreenFactoryFactory {
    PageFactory newFactory(List values);
}
