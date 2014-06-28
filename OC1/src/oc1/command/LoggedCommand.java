package oc1.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import oc1.log.LogManager;

/**
 *
 * @author Curt
 */
public abstract class LoggedCommand 
    extends Command
{
    public LoggedCommand(String command) {
        super(command);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        log("Executing " + getCommandName());
        go();
    }

    abstract protected void go();
    
    private void log(String message) {
        LogManager.of().getLog(LoggedCommand.class).log(message);    
    }

}
