package c1.ui;

import common.ui.IForm;

/**
 * For creating new FormS.
 * This exists to provide a single spot for Form configuration.
 * See DebugForm.
 * @author Curt
 */
public interface IFormFactory {
    IForm newForm(String title);
}
