package hash;

/**
 * For running a method from source in a given context.
 * @author Curt
 */
public final class Run {
    
    private final String source;
    private final String method;
    private final NamedValueProvider values;

    Run(String source, String method,NamedValueProvider values) {
        this.source = source;
        this.method = method;
        this.values = values;
    }
    
    public static final class Builder {
        private String source;
        private String method;
        private NamedValueProvider context;
        
        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder context(NamedValueProvider context) {
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
        return hash().invoke(method, Args.valuesFor(args), combinedHashAndSuppliedContext());
    }
    
    private Context combinedHashAndSuppliedContext() {
        return new Context("#",new CompositeNamedValueProvider(values,hash()));
    }
    
    private Hash hash() {
        return new Parser().parse(source);
    }

}
