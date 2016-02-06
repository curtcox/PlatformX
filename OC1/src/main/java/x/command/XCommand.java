package x.command;

import x.app.Registry;
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

    /**
     * To execute a command without args.
     */
    public void go() {
        log("Executing");
        action();
    }

    /**
     * To execute a command with args.
     * @param args command specific args
     */
    public void go(Object... args) {
        log("Executing",args);
        action(args);
    }

    /**
     * Implementors must override this to do whatever the command does.
     * @param args that were supplied by go
     */
    protected abstract void action(Object... args);

    private void log(String message) {
        getLog().log(message,command);
    }

    private void log(String message,Object... args) {
        getLog().log(message,command,args);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(this);
    }

}
