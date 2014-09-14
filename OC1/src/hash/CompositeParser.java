package hash;

/**
 *
 * @author Curt
 */
class CompositeParser
    implements IParser
{
    final IParser[] components;
    
    CompositeParser(IParser... components) {
        this.components = components;            
    }
    
    public boolean canParse(Tokens tokens) {
        for (IParser component : components) {
            if (component.canParse(tokens)) {
                return true;
            }
        }
        return false;
    }

    public Object parse(Tokens tokens) {
        for (IParser component : components) {
            if (component.canParse(tokens)) {
                return component.parse(tokens);
            }
        }
        throw new IllegalArgumentException(tokens.toString());
    }
    
}
