package c1.screenfactories;

import java.util.List;

public final class IndexScreenFactory {

    public static AbstractItemListScreenFactory of(final List<String> index) {
        return new AbstractItemListScreenFactory(new ListValueSupplier(index));
    }
}
