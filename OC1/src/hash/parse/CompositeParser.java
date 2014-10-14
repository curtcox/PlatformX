package hash.parse;

import hash.lex.Tokens;

class CompositeParser
    extends AbstractParser
{
    final IParser[] components;
    
    CompositeParser(IParser... components) {
        this.components = components;            
    }
    
    public boolean canParseTokens(Tokens tokens) {
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
