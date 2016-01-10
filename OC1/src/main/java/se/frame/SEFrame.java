package se.frame;

import javax.swing.*;

public final class SEFrame
    extends JFrame
{
    public final FrameMeta meta;

    public SEFrame(FrameMeta meta) {
        this.meta = meta;
        setTitle(meta.what_its_for);
    }
}
