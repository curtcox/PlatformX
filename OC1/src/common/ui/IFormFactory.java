package common.ui;

import common.screen.ScreenLink;
import common.ui.IForm;

/**
 * For creating new FormS.
 * This exists to provide a single spot for Form configuration.
 * See DebugForm.
 */
public interface IFormFactory {
    IForm newForm(ScreenLink link);
}
