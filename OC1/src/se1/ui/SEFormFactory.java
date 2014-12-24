package se1.ui;

import oc1.ui.IForm;
import oc1.ui.IFormFactory;

public final class SEFormFactory
    implements IFormFactory
{
    @Override
    public IForm newForm(String title) {
        return new SEForm(title);
    }
}
