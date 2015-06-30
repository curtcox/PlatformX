package se.util;

import x.util.Runner;

import java.awt.*;

public final class SERunner
    implements Runner
{
    @Override
    public void invokeLater(Runnable runnable) {
        EventQueue.invokeLater(runnable);
    }
}
