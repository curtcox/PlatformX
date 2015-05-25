package common.screenfactories;

import c1.screenfactories.ValueSupplier;

import java.util.List;

public final class ListValueSupplier
    implements ValueSupplier
{
    private final List values;

    public ListValueSupplier(List values) {
        this.values = values;
    }

    @Override
    public List getValues() {
        return values;
    }
}
