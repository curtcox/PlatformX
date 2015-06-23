package common.screenfactories;

import common.screen.PageFactory;

import java.util.List;

public interface ItemListScreenFactoryFactory {
    PageFactory newFactory(List values);
}
