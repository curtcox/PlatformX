package se.ui;

import common.screen.ScreenLink;
import common.ui.IForm;
import common.ui.IFormFactory;

public final class SEFormFactory
    implements IFormFactory
{
    @Override
    public IForm newForm(ScreenLink link) {
        return new SEForm(link);
    }
}
