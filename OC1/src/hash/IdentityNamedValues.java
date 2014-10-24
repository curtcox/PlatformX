package hash;

final class IdentityNamedValues
    implements NamedValues
{

    public Expression get(String name) {
        return new StringConstant(name);
    }
    
}
