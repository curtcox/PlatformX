package an.a22.uilist;

import x.uilist.ListCellConfigurer;

final class AnBasicListCellRenderer<T>
{
    private final ListCellConfigurer configurer;
    private final AnListCell cell = new AnListCell();

    AnBasicListCellRenderer(ListCellConfigurer configurer) {
        this.configurer = configurer;
    }

    AnListCell getListCellRendererView(T value) {
        configurer.configureButton(cell,value);
        return cell;
    }
}
