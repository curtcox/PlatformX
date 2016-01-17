package se.commands;

import se.events.Events;
import se.ui.EditLinkEvent;
import se.events.EditTaggedValueEvent;
import se.util.MutableTaggedValue;
import se.util.TaggedValue;
import se.util.TaggedValueStringMap;
import x.app.Registry;
import x.command.XCommand;
import x.page.Page;
import x.page.PageLink;
import x.screen.Screen;
import x.uiwidget.XComponent;

/**
 * For launching a source code editor for the current page.
 */
public final class EditCommand
    extends XCommand
{

    private EditCommand() {
        super("Edit");
    }

    public static EditCommand of() {
        return new EditCommand();
    }

    @Override
    protected void action(Object... args) {
        PageLink link = (PageLink) args[0];
        XComponent layout = (XComponent) args[1];
        postEdit(link,layout);
    }

    private void postEdit(PageLink link, XComponent layout) {
        TaggedValue[] values = stringMap().getValuesFor(link.tags);
        if (values.length==0) {
            postEditToNewTaggedValue(link,layout);
        } else if (values.length==1) {
            postEventToEditSingleSource((MutableTaggedValue) values[0],layout);
        } else {
            postEventForAmbiguousSelection(link, layout);
        }
    }

    private void postEditToNewTaggedValue(PageLink link, XComponent layout) {
        MutableTaggedValue value = (MutableTaggedValue) stringMap().newValue(link.tags);
        postEventToEditSingleSource(value,layout);
    }

    private void postEventToEditSingleSource(MutableTaggedValue value, XComponent layout) {
        events().post(new EditTaggedValueEvent(value,page(),layout));
    }

    private Page page() {
        return Screen.getShowing().page;
    }

    private void postEventForAmbiguousSelection(PageLink link, XComponent layout) {
        events().post(new EditLinkEvent(link,layout));
    }

    Events events() {
        return Registry.get(Events.class);
    }

    TaggedValueStringMap stringMap() {
        return Registry.get(TaggedValueStringMap.class);
    }
}
