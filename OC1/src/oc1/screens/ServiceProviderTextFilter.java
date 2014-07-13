package oc1.screens;

import oc1.domain.ServiceProvider;
import oc1.ui.ListFilter;
import oc1.ui.StringToListFilter;
import oc1.util.Strings;

/**
 *
 * @author Curt
 */
final class ServiceProviderTextFilter
    implements StringToListFilter
{

    public ListFilter listFilterFor(final String text) {
        return new ListFilter() {
            public boolean passes(Object item) {
                String trimmed = text.trim().toLowerCase();
                ServiceProvider provider = (ServiceProvider) item;
                return isIn(trimmed, provider.name) || isIn(trimmed, provider.address);
            }
        };
    }

    private static boolean isIn(String target, Object object) {
        return object!=null && Strings.contains(object.toString().toLowerCase(), target);
    }
}
