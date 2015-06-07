package se.uilist;

import common.uilist.ListCellConfigurer;

import javax.swing.*;

public final class SEBasicListCellRenderer<T>
    implements ListCellRenderer<T>
{
    private final ListCellConfigurer configurer;

    public SEBasicListCellRenderer(ListCellConfigurer configurer) {
        this.configurer = configurer;
    }

    @Override
    public SEListCell getListCellRendererComponent(JList<? extends T> list, T value, int index,
             boolean isSelected,
             boolean cellHasFocus)
    {
        SEListCell cell = new SEListCell();
        configurer.configureButton(cell,value);
        return cell;
    }
}
