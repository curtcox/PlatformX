package x.util;

public interface Translator {

    Object translate(Object o);

    Translator IDENTITY = new Translator() {
        @Override
        public Object translate(Object o) {
            return o;
        }
    };
}
