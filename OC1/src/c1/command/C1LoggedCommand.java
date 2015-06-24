package c1.command;

import com.codename1.ui.events.ActionEvent;
import x.Registry;
import x.command.Command;
import x.log.ILog;
import x.log.ILogManager;

public class C1LoggedCommand
    extends com.codename1.ui.Command
{
    final Command command;

    public C1LoggedCommand(Command command) {
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
