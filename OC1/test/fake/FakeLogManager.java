package fake;

import x.log.ILog;
import x.log.ILogManager;

public class FakeLogManager
    implements ILogManager
{
    @Override
    public ILog getLog(Class c) {
        return new FakeLog();
    }
}
