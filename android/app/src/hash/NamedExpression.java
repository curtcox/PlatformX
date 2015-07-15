package hash;

public abstract class NamedExpression
    implements Expression
{

    public static NamedValues namedValues(final Hash hash,final NamedExpression... expressions) {
        return new NamedValues() {
            public Expression get(String name) {
                for (NamedExpression expression : expressions) {
                    if (expression.name.equals(name)) {
                        return expression;
                    }
                }
                return hash.get(name);
            }
        };
    }

    public static NamedValues namedValues(final NamedExpression... expressions) {
        return new NamedValues() {
            public Expression get(String name) {
                for (NamedExpression expression : expressions) {
                    if (expression.name.equals(name)) {
                        return expression;
                    }
                }
                return null;
            }
        };
    }

    public final String name;
    
    public NamedExpression(String name) {
        this.name = name;
    }

    public Object invokeIn(Context context) {
        Object[] values = new Object[context.values.length];
        for (int i=0; i<values.length; i++) {
            values[i] = context.values[i].invokeIn(context);
        }
        return invoke(values);
    }

    @Override
    public String toString() {
        return name;
    }
    
    public abstract Object invoke(Object[] values);
}
