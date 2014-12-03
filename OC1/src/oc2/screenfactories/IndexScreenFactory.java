package oc2.screenfactories;

import java.util.List;
import oc2.screen.RootScreenFactory;

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
