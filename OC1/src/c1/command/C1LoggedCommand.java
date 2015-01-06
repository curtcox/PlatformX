package c1.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import common.Registry;
import common.command.LoggedCommand;
import common.log.ILog;
import common.log.ILogManager;

public class C1LoggedCommand
    extends Command
{
    final LoggedCommand command;

    public C1LoggedCommand(LoggedCommand command) {
        super(command.command);
        this.command = command;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        log("Executing " + getCommandName());
        command.go();
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(C1LoggedCommand.class);
    }

}
