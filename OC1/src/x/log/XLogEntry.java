package x.log;

import x.screen.Screen;

final class XLogEntry {

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
        return screen.toString();
    }

    private String prefix() {
        return clazz.getCanonicalName();
    }

    public String toString() {
        return time() + ":" + thread() + ":" + screen() + ":" + prefix() + ":" + message;
    }

}
