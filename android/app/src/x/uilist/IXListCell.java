package x.uilist;

/**
 * A cell in a list.
 */
public interface IXListCell {

    void apply(Config config);

    /**
     * A config that can be applied to a cell.
     */
    class Config {
        public final String first;
        public final String second;
        public final Object icon;
        public Config(String first, String second, Object icon) {
            this.first = first;
            this.second = second;
            this.icon = icon;
        }
        public Config(String first) {
            this(first,"",null);
        }
    }

    /**
     * For producing a list cell Config.
     * @param <T> the kind of items in the list
     */
    interface ConfigProducer<T> {
        IXListCell.Config configFor(T value);
    }
}
