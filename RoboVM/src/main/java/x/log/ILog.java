package x.log;

public interface ILog {
    void log(Throwable e);
    void log(String message);
}
