package x.pagefactories;

import x.uilist.IXListCell;

public final class CellConfigurer
        implements IXListCell.ConfigProducer
{
    @Override
    public IXListCell.Config configFor(Object item) {
        return new IXListCell.Config(item.toString());
    }
}
