package c1.screenfactories;

import common.screenfactories.ListValueSupplier;

import java.util.List;

public final class IndexScreenFactory {

    public static ItemListScreenFactory of(final List<String> index) {
        return new ItemListScreenFactory(new ListValueSupplier(index));
    }
}
