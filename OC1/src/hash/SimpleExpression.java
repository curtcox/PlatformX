package hash;

/**
 *
 * @author Curt
 */
abstract class SimpleExpression
    implements Expression
{

    static NamedValues newContext(final Hash hash,final SimpleExpression... expressions) {
        return new NamedValues() {
            public Expression get(String name) {
                for (SimpleExpression expression : expressions) {
                    if (expression.name.equals(name)) {
                        return expression;
                    }
                }
                return hash.get(name);
            }
        };
    }

    static NamedValues newContext(final SimpleExpression... expressions) {
        return new NamedValues() {
            public Expression get(String name) {
                for (SimpleExpression expression : expressions) {
                    if (expression.name.equals(name)) {
                        return expression;
                    }
                }
                return null;
            }
        };
    }

    final String name;
    
    SimpleExpression(String name) {
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
