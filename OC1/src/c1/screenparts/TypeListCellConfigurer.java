package c1.screenparts;

import common.domain.Type;
import c1.uilist.ListCell;
import c1.uilist.ListCellConfigurer;
import common.util.Strings;

public final class TypeListCellConfigurer
    implements ListCellConfigurer<Type>
{
    public void configureButton(ListCell button, Type type) {
        button.firstRow.setText(friendly(type.toString()));
    }

    private static String friendly(String type) {
        return Strings.replace(type,"_"," ");
    }
}
