package c1.screenfactories;

import common.screenfactories.ListValueSupplier;

import java.util.List;

public final class IndexScreenFactory {

    public static C1ItemListScreenFactory of(final List<String> index) {
        return new C1ItemListScreenFactory(new ListValueSupplier(index));
    }
}
