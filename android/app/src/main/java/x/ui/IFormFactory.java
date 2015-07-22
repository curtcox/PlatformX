package x.ui;

import x.page.PageLink;

/**
 * For creating new FormS.
 * Applications don't need to implement this interface.
 * This exists to provide a single spot for Form fabrication and configuration.
 * See DebugForm.
 */
public interface IFormFactory {
    IForm newForm(PageLink link);
}
