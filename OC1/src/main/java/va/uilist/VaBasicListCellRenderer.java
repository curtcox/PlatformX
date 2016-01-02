package va.uilist;

import com.vaadin.ui.Table;
import x.uilist.IXListCell;

final class VaBasicListCellRenderer<T>
{
    private final IXListCell.ConfigProducer configurer;

    public VaBasicListCellRenderer(IXListCell.ConfigProducer configurer) {
        this.configurer = configurer;
    }

    public VaListCell getListCellRendererComponent(Table list, T value, int index,
             boolean isSelected,
             boolean cellHasFocus)
    {
        VaListCell cell = new VaListCell();
        cell.apply(configurer.configFor(value));
        return cell;
    }
}
