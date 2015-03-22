package se.ui;

import common.command.Command;
import common.screen.ScreenLink;
import common.uiwidget.UIComponent;
import se.events.Events;

public final class EditCommand
    extends Command
{
    public static class Value {
        final ScreenLink link;
        final UIComponent layout;

        Value(ScreenLink link, UIComponent layout) {
            this.link = link;
            this.layout = layout;
        }
    }

    EditCommand() {
        super("Edit");
    }

    @Override
    protected void action(Object... args) {
        ScreenLink link = (ScreenLink) args[0];
        UIComponent layout = (UIComponent) args[1];
    }

    Events events() {
        return null;
    }
}
