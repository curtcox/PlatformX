package x.uilist;

public interface StringToListFilter {

    static StringToListFilter DEFAULT = new TextFilter();

    ListFilter listFilterFor(String text);
}
