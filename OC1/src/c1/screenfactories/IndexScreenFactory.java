package c1.screenfactories;

import java.util.List;
import c1.screen.RootScreenFactory;

public final class IndexScreenFactory 
    extends AbstractItemListScreenFactory
{
    private final List<String> index;

    public IndexScreenFactory(List<String> index) {
        super("Index");
        this.index = index;
    }

    protected List<String> getValues() {
        return index;
    }
}
