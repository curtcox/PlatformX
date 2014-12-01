package oc1.screenparts;

import oc1.domain.Type;
import oc1.uilist.ListFilter;
import oc1.uilist.StringToListFilter;
import oc1.util.Strings;

public final class TypeTextFilter
    implements StringToListFilter
{

    public ListFilter listFilterFor(final String text) {
        return new ListFilter() {
            public boolean passes(Object item) {
                String trimmed = text.trim().toLowerCase();
                Type type = (Type) item;
                return isIn(trimmed, type);
            }
        };
    }

    private static boolean isIn(String target, Object object) {
        return object!=null && Strings.contains(object.toString().toLowerCase(), target);
    }
}
