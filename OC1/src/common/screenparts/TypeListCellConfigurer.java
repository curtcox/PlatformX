package common.screenparts;

import common.domain.Type;
import common.uilist.IListCell;
import common.uilist.ListCellConfigurer;
import common.util.Strings;

public final class TypeListCellConfigurer
    implements ListCellConfigurer<Type>
{
    public void configureButton(IListCell button, Type type) {
        button.setFirstRowText(friendly(type.toString()));
    }

    private static String friendly(String type) {
        return Strings.replace(type,"_"," ");
    }
}
