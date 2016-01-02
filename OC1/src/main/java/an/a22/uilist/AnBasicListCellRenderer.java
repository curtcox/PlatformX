package an.a22.uilist;

import x.uilist.IXListCell;

final class AnBasicListCellRenderer<T>
{
    private final IXListCell.ConfigProducer configurer;

    AnBasicListCellRenderer(IXListCell.ConfigProducer configurer) {
        this.configurer = configurer;
    }

    AnListCell getListCellRendererView(AnListCell cell, T value) {
        cell.apply(configurer.configFor(value));
        return cell;
    }
}
