package se.uilist;

import common.uilist.ListCellConfigurer;

import javax.swing.*;
import java.awt.*;

public final class SEBasicListCellRenderer<T>
    implements ListCellRenderer<T>
{
    public SEBasicListCellRenderer(ListCellConfigurer configurer) {

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends T> jList, T t, int i, boolean b, boolean b1) {
        return null;
    }
}
