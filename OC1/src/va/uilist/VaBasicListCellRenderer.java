package va.uilist;

import com.vaadin.ui.Table;
import x.uilist.ListCellConfigurer;

final class VaBasicListCellRenderer<T>
{
    private final ListCellConfigurer configurer;

    public VaBasicListCellRenderer(ListCellConfigurer configurer) {
        this.configurer = configurer;
    }

    public VaListCell getListCellRendererComponent(Table list, T value, int index,
             boolean isSelected,
             boolean cellHasFocus)
    {
        VaListCell cell = new VaListCell();
        configurer.configureButton(cell,value);
        return cell;
    }
}
