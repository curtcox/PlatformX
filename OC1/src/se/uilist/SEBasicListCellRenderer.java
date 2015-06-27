package se.uilist;

import x.uilist.ListCellConfigurer;

import javax.swing.*;

final class SEBasicListCellRenderer<T>
    implements ListCellRenderer<T>
{
    private final ListCellConfigurer configurer;

    SEBasicListCellRenderer(ListCellConfigurer configurer) {
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
