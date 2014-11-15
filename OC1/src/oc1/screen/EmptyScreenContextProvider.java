package oc1.screen;

final class EmptyScreenContextProvider
    implements ScreenContext.Provider
{
    public ScreenContext getContext() {
        return new ScreenContext();
    }
}
