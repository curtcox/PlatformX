package x.pageparts;

import x.domain.Type;
import x.uilist.IXListCell;
import x.util.Strings;

public final class TypeListCellConfigurer
    implements IXListCell.ConfigProducer<Type>
{
    private static String friendly(String type) {
        return Strings.replace(type,"_"," ");
    }

    @Override
    public IXListCell.Config configFor(Type type) {
        return new IXListCell.Config(friendly(type.toString()));
    }
}
