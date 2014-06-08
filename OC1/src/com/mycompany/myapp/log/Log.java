package com.mycompany.myapp.log;

/**
 *
 * @author Curt
 */
public final class Log {

    final Class clazz;
    final String prefix;
    
    Log(Class clazz) {
        this.clazz = clazz;
        prefix = ":" + clazz.getCanonicalName() + ":";
    }

    public void log(Exception e) {
        log("Caught exception");
        log("class=" + e.getClass());
        log("message=" + e.getMessage());
        e.printStackTrace();
    }

    public void log(String message) {
        System.out.println(now() + prefix + message);
    }
    
    String now() {
        return hex(System.currentTimeMillis());
    }

    private String hex(long value) {
        return "" + value;
    }
}
