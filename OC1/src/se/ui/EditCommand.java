package se.ui;

import common.Registry;
import common.command.Command;
import common.screen.ScreenLink;
import common.uiwidget.UIComponent;
import se.events.Events;
import se.util.SimpleTaggedValue;
import se.util.TaggedValue;
import se.util.TaggedValueStringMap;

public final class EditCommand
    extends Command
{

    EditCommand() {
        super("Edit");
    }

    @Override
    protected void action(Object... args) {
        ScreenLink link = (ScreenLink) args[0];
        TaggedValue[] values = stringMap().getValuesFor(link.tags);
        if (values.length==1) {
            events().post(new EditTaggedValueEvent(values[0]));
        } else {
            UIComponent layout = (UIComponent) args[1];
            events().post(new EditLinkEvent(link,layout));
        }
    }

    Events events() {
        return Registry.get(Events.class);
    }

    TaggedValueStringMap stringMap() {
        return Registry.get(TaggedValueStringMap.class);
    }
}
