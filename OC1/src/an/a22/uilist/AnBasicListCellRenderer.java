package an.a22.uilist;

import javafx.scene.control.ListView;
import x.uilist.ListCellConfigurer;

final class AnBasicListCellRenderer<T>
{
    private final ListCellConfigurer configurer;

    public AnBasicListCellRenderer(ListCellConfigurer configurer) {
        this.configurer = configurer;
    }

    public AnListCell getListCellRendererComponent(ListView<? extends T> list, T value, int index,
             boolean isSelected,
             boolean cellHasFocus)
    {
        AnListCell cell = new AnListCell();
        configurer.configureButton(cell,value);
        return cell;
    }
}
