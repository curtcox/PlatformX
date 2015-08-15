package hash.parse;

import hash.Method;
import hash.lex.Tokens;

final class MethodParser
    extends CompositeParser
{
    MethodParser() {
        super(new ValidMethodParser(),new InvalidMethodParser());
    }
    
    @Override
    public Method parse(Tokens tokens) {
        return (Method) super.parse(tokens);
    }
}
