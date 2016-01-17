package se.commands;

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

    }
}
