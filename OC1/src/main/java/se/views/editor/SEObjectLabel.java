package se.views.editor;

import javax.swing.*;

public final class SEObjectLabel
    extends JLabel
{
    private final String contents;
    private Object value;

    public SEObjectLabel(String contents) {
        this.contents = contents;
    }

    public void set(Object value) {
        this.value = value;
        setText(contents + "=" + value);
    }
}
