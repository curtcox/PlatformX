package common.ui;

public interface IDisplay {

    boolean isPortrait();
    IForm getCurrent();
    void execute(String url);
}
