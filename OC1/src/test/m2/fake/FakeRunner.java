package fake;

import x.util.Runner;

public class FakeRunner
    implements Runner
{
    @Override
    public void invokeLater(Runnable runnable) {
        runnable.run();
    }
}
