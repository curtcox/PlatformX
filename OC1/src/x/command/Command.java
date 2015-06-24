package x.command;

import x.log.ILog;
import x.log.ILogManager;
import x.Registry;

public abstract class Command {
    public final String command;

    public Command(String command) {
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
        return Registry.get(ILogManager.class).getLog(Command.class);
    }

}
