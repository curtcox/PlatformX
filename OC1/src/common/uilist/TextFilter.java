package common.uilist;

import common.util.Strings;

final class TextFilter
    implements StringToListFilter
{
    public ListFilter listFilterFor(final String text) {
        return new ListFilter() {
            public boolean passes(Object item) {
                String trimmed = text.trim().toLowerCase();
                return isIn(trimmed, item);
            }
        };
    }

    private static boolean isIn(String target, Object object) {
        return object != null && Strings.contains(object.toString().toLowerCase(), target);
    }
}