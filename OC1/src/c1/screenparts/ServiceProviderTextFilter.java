package c1.screenparts;

import common.domain.ServiceProvider;
import common.uilist.ListFilter;
import common.uilist.StringToListFilter;
import common.util.Strings;

/**
 *
 * @author Curt
 */
public final class ServiceProviderTextFilter
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
