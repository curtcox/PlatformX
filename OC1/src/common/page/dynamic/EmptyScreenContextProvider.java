package common.page.dynamic;

final class EmptyScreenContextProvider
    implements ScreenContext.Provider
{
    public ScreenContext getContext() {
        return new ScreenContext();
    }
}
