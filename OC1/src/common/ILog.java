package common;

public interface ILog {
    void log(Throwable e);
    void log(String message);
}
