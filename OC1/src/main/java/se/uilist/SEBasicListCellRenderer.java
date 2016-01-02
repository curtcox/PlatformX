package se.uilist;

import x.uilist.IXListCell;

import javax.swing.*;

final class SEBasicListCellRenderer<T>
    implements ListCellRenderer<T>
{
    private final IXListCell.ConfigProducer configurer;
    private final SEListCell cell = new SEListCell();

    SEBasicListCellRenderer(IXListCell.ConfigProducer configurer) {
        this.configurer = configurer;
    }

    @Override
    public SEListCell getListCellRendererComponent(JList<? extends T> list, T value, int index,
             boolean isSelected,
             boolean cellHasFocus)
    {
        cell.apply(configurer.configFor(value));
        return cell;
    }
}
