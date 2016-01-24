package se.commands;

import se.events.Events;
import se.events.ViewObjectEvent;
import x.app.Registry;
import x.command.XCommand;

public class ViewObjectCommand
    extends XCommand
{
    private ViewObjectCommand() {
        super("View Object");
    }

    public static ViewObjectCommand of() {
        return new ViewObjectCommand();
    }

    @Override
    protected void action(Object... args) {
        events().post(new ViewObjectEvent(args[0]));
    }

    Events events() {
        return Registry.get(Events.class);
    }
}
