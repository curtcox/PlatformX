package hash;

import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;

import java.util.Arrays;

/**
 * For checking a series of NamedValues until a match is found.
 * @author Curt
 */
final class CompositeNamedValues
    implements NamedValues
{
    final NamedValues[] providers;
    
    CompositeNamedValues(NamedValues... providers) {
        this.providers = providers;
    }
    
    public Expression get(String name) {
        for (NamedValues provider : providers) {
            Expression value = provider.get(name);
            if (value!=null) {
                return value;
            }
        }
        String message = "No value found for " + name + " in " + Arrays.asList(providers);
        log(new IllegalArgumentException(message));
        log(message);
        return null;
    }
    
    private void log(Exception e) {
        getLog().log(e);    
    }

    private void log(String message) {
        getLog().log(message);    
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(this);
    }

}
