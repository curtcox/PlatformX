package common.screenfactories;

import common.screen.ScreenFactory;

import java.util.List;

public interface ItemListScreenFactoryFactory {
    ScreenFactory newFactory(List values);
}
