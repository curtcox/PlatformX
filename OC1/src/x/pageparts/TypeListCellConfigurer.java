package x.pageparts;

import x.domain.Type;
import x.uilist.IXListCell;
import x.uilist.ListCellConfigurer;
import x.util.Strings;

public final class TypeListCellConfigurer
    implements ListCellConfigurer<Type>
{
    public void configureButton(IXListCell button, Type type) {
        button.setFirstRowText(friendly(type.toString()));
    }

    private static String friendly(String type) {
        return Strings.replace(type,"_"," ");
    }
}
