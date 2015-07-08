package org.robovm.apple.uikit;

import org.robovm.apple.foundation.*;

public class UIViewController
        extends UIResponder
        implements NSCoding, UIAppearanceContainer, UITraitEnvironment, UIStateRestoring, NSExtensionRequestHandling
{
    @Override
    public void encode(NSCoder nsCoder) {

    }

    @Override
    public void beginRequest(NSExtensionContext nsExtensionContext) {

    }

    @Override
    public UIStateRestoring getRestorationParent() {
        return null;
    }

    @Override
    public Class<?> getObjectRestorationClass() {
        return null;
    }

    @Override
    public void encodeRestorableState(NSCoder nsCoder) {

    }

    @Override
    public void decodeRestorableState(NSCoder nsCoder) {

    }

    @Override
    public void applicationFinishedRestoringState() {

    }

    @Override
    public UITraitCollection getTraitCollection() {
        return null;
    }

    @Override
    public void traitCollectionDidChange(UITraitCollection uiTraitCollection) {

    }

    public NSArray<UIViewController> getChildViewControllers() {
        return null;
    }
}
