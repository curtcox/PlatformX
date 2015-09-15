package x.command;

import x.Registry;
import x.log.ILog;
import x.log.ILogManager;

/**
 * A platform-independent command abstraction.
 */
public abstract class XCommand {
    public final String command;

    public XCommand(String command) {
        this.command = command;
    }
    
    public void go() {
        log("Executing " + command);
        action();
    }

    public void go(Object... args) {
        log("Executing " + command + args);
        action(args);
    }

    protected abstract void action(Object... args);

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(XCommand.class);
    }

}
