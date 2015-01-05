package common.command;

import common.ICommand;
import common.log.ILog;
import common.log.ILogManager;
import common.Registry;

public abstract class LoggedCommand
    implements ICommand
{
    final String command;

    public LoggedCommand(String command) {
        this.command = command;
    }
    
    @Override
    public void go() {
        log("Executing " + command);
        go();
    }

    protected abstract void action();

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(LoggedCommand.class);
    }

}
