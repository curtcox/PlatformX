package se.ui;

import common.command.Command;
import common.screen.ScreenLink;
import common.uiwidget.UIComponent;

final class EditCommand
    extends Command
{
    public ScreenLink link;
    public UIComponent layout;

    EditCommand() {
        super("Edit");
    }

    @Override
    protected void action(Object... args) {
        link = (ScreenLink) args[0];
        layout = (UIComponent) args[1];
    }
}
