package fake;

import common.log.ILog;
import common.log.ILogManager;

public class FakeLogManager
    implements ILogManager
{
    @Override
    public ILog getLog(Class c) {
        return new FakeLog();
    }
}
