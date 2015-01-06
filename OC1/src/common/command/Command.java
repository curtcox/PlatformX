package common.command;

import common.log.ILog;
import common.log.ILogManager;
import common.Registry;

public abstract class Command {
    public final String command;

    public Command(String command) {
        this.command = command;
    }
    
    public void go() {
        log("Executing " + command);
        action();
    }

    protected abstract void action();

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(Command.class);
    }

}
