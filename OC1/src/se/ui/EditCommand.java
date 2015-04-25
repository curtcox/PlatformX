package se.ui;

import common.Registry;
import common.command.Command;
import common.screen.ScreenLink;
import common.uiwidget.UIComponent;
import se.events.Events;
import se.util.SimpleTaggedValue;

public final class EditCommand
    extends Command
{

    EditCommand() {
        super("Edit");
    }

    @Override
    protected void action(Object... args) {
        ScreenLink link = (ScreenLink) args[0];
        UIComponent layout = (UIComponent) args[1];
        events().post(new EditLinkEvent(link,layout));
        SimpleTaggedValue taggedValue = new SimpleTaggedValue();
        taggedValue.setTags(link.tags);
        events().post(new EditTaggedValueEvent(taggedValue));
    }

    Events events() {
        return Registry.get(Events.class);
    }
}
