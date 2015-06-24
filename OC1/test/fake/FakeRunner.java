package fake;

import x.util.Runner;

public class FakeRunner
    implements Runner
{
    @Override
    public void run(Runnable runnable) {
        runnable.run();
    }
}
