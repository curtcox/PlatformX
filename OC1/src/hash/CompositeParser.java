package hash;

/**
 *
 * @author Curt
 */
class CompositeParser
    implements IParser
{

    CompositeParser(IParser... components) {
            
    }
    
    public boolean canParse(Tokens tokens) {
        return false;
    }

    public Object parse(Tokens tokens) {
        return null;
    }
    
}
