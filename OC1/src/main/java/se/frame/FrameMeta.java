package se.frame;

import x.app.Registry;

/**
 * For storing information about a UI frame.
 */
public final class FrameMeta {

    public final String what_its_for;
    public final String how_to_use_it;
    public final String source_code_location;
    public final String source_code;

    public FrameMeta(String why, String usage, String location, String code) {
       what_its_for = why;
       how_to_use_it = usage;
       source_code_location = location;
       source_code = code;
    }

    public FrameMeta(String why, String usage, Class clazz) {
        this(why,usage,location(clazz),code(clazz));
    }

    private static String code(Class clazz) {
        return lookup().sourceFor(clazz);
    }

    private static String location(Class clazz) {
        return clazz.getName();
    }

    private static JavaSourceCodeLookup lookup() {
        return Registry.get(JavaSourceCodeLookup.class);
    }
}
