package se.ui;

import common.command.Command;
import common.uiwidget.UIComponent;

final class EditCommand
    extends Command
{
    public String title;
    public UIComponent layout;

    EditCommand() {
        super("Edit");
    }

    @Override
    protected void action(Object... args) {
        title = (String) args[0];
        layout = (UIComponent) args[1];
    }
}
