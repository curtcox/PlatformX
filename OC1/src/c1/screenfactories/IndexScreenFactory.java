package c1.screenfactories;

import java.util.List;
import c1.screen.RootScreenFactory;

public final class IndexScreenFactory 
    extends AbstractItemListScreenFactory
{

    public IndexScreenFactory() {
        super("Index");
    }

    protected List<String> getValues() {
        return RootScreenFactory.index;
    }
}
