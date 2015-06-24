package common.pageparts;

import common.domain.Type;
import common.uilist.ListFilter;
import common.uilist.StringToListFilter;
import common.util.Strings;

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
