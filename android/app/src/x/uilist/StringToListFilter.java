package x.uilist;

public interface StringToListFilter {

    StringToListFilter DEFAULT = new TextFilter();

    ListFilter listFilterFor(String text);
}
