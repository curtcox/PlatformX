package x.pageparts;

import x.domain.ServiceProvider;
import x.uilist.ListFilter;
import x.uilist.StringToListFilter;
import x.util.Strings;

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
