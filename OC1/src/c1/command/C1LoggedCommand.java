package c1.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import common.ICommand;
import common.Registry;
import common.log.ILog;
import common.log.ILogManager;

public abstract class C1LoggedCommand
    extends Command
    implements ICommand
{
    public C1LoggedCommand(String command) {
        super(command);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        log("Executing " + getCommandName());
        go();
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(C1LoggedCommand.class);
    }

}
