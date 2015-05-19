package c1.screenfactories;

import java.util.List;

public final class IndexScreenFactory 
    extends AbstractItemListScreenFactory
{
    private final List<String> index;

    public IndexScreenFactory(List<String> index) {
        this.index = index;
    }

    protected List<String> getValues() {
        return index;
    }
}
