package oc1.screenparts;

import oc1.domain.Type;
import oc1.uilist.ListCell;
import oc1.uilist.ListCellConfigurer;
import oc1.util.Strings;

/**
 *
 * @author Curt
 */
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
