package ios.uilist;

import javafx.scene.control.ListView;
import x.uilist.ListCellConfigurer;

final class IosBasicListCellRenderer<T>
{
    private final ListCellConfigurer configurer;

    public IosBasicListCellRenderer(ListCellConfigurer configurer) {
        this.configurer = configurer;
    }

    public IosListCell getListCellRendererComponent(ListView<? extends T> list, T value, int index,
             boolean isSelected,
             boolean cellHasFocus)
    {
        IosListCell cell = new IosListCell();
        configurer.configureButton(cell,value);
        return cell;
    }
}
