package hash;

/**
 * A NamedValue that produces a StringConstant of any given name.
 * @author Curt
 */
final class IdentityNamedValues
    implements NamedValues
{

    public Expression get(String name) {
        return new StringConstant(name);
    }
    
}
