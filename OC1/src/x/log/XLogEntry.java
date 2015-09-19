package x.log;

import x.event.LiveList;
import x.event.XLiveList;
import x.pagefactories.NamedValue;
import x.pagefactories.NamedValueListSource;
import x.screen.Screen;

import java.util.Arrays;

public final class XLogEntry
    implements NamedValueListSource
{

    final Class clazz;
    final String message;
    final Thread thread;
    final Screen screen;
    final long time;

    XLogEntry(Class clazz, String message) {
        this.clazz = clazz;
        this.screen = Screen.getShowing();
        this.message = message;
        this.thread = Thread.currentThread();
        this.time = System.currentTimeMillis();
    }

    public static XLogEntry of(Class clazz,String message) {
        return new XLogEntry(clazz,message);
    }

    String time() {
        return hex(System.currentTimeMillis());
    }

    private String hex(long value) {
        return "" + value;
    }

    String thread() {
        return thread.getName();
    }

    private String screen() {
        return "" + screen;
    }

    private String prefix() {
        return clazz.getCanonicalName();
    }

    public String toString() {
        return time() + ":" + thread() + ":" + screen() + ":" + prefix() + ":" + message;
    }

    @Override
    public LiveList<NamedValue> asNamedValues() {
        return XLiveList.of(Arrays.asList(
            new NamedValue("clazz",clazz),
            new NamedValue("message",message),
            new NamedValue("thread",thread),
            new NamedValue("screen",screen),
            new NamedValue("time",time)
        ));
    }
}
