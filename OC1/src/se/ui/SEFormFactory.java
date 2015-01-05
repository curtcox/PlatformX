package se.ui;

import common.ui.IForm;
import common.ui.IFormFactory;

public final class SEFormFactory
    implements IFormFactory
{
    @Override
    public IForm newForm(String title) {
        return new SEForm(title);
    }
}
