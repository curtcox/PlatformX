package oc1.util;

/**
 *
 * @author Curt
 */
public final class Glob {

    private final String glob;
    
    public Glob(String glob) {
        this.glob = glob.toLowerCase();
    }

    public boolean matches(String string) {
        return glob.equals("*") || glob.equals(string.toLowerCase());
    }
    
}
