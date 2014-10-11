package hash;

/**
 * For running a method from source in a given context.
 * @author Curt
 */
public final class Run {
    
    private final String source;
    private final String method;
    private final Context context;

    Run(String source, String method,Context context) {
        this.source = source;
        this.method = method;
        this.context = context;
    }
    
    public static final class Builder {
        private String source;
        private String method;
        private Context context;
        
        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Object args(Object...args) {
            Run run = new Run(source,method,context);
            return run.invoke(args);
        }
    }
    
    public static Builder source(String source) {
        Builder builder = new Builder();
        builder.source = source;
        return builder;
    }
    
    private Object invoke(Object... args) {
        return hash().invoke(method, Args.valuesFor(args), context());
    }
    
    private Context context() {
        return context;
    }
    
    private Hash hash() {
        return new Parser().parse(source);
    }

}
