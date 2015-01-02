package se.ui;

import common.ui.IForm;
import c1.ui.IFormFactory;

public final class SEFormFactory
    implements IFormFactory
{
    @Override
    public IForm newForm(String title) {
        return new SEForm(title);
    }
}
