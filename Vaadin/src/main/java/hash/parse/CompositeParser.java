package hash.parse;

import hash.lex.Tokens;

class CompositeParser
    extends AbstractParser
{
    final IParser[] components;
    
    CompositeParser(IParser... components) {
        this.components = components;            
    }
    
    final public boolean canParseTokens(Tokens tokens) {
        for (IParser component : components) {
            if (component.canParse(tokens)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Implementations only override this to provide a more specific return type.
     */
    public Object parse(Tokens tokens) {
        for (IParser component : components) {
            if (component.canParse(tokens)) {
                return component.parse(tokens);
            }
        }
        throw new IllegalArgumentException(tokens.toString());
    }
    
}
