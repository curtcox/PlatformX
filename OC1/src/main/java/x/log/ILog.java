package x.log;

/**
 * For generating a list of things to log.
 * Use a log manager to get one.
 */
public interface ILog {
    void log(Throwable e);
    void log(String message, Object... details);
}
