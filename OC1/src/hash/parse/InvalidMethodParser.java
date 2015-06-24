package hash.parse;

import hash.Identifier;
import hash.Method;
import hash.SyntaxError;
import hash.lex.Tokens;

import static hash.SyntaxError.Type.*;

/**
 * For parsing methods with syntax errors into syntax errors.
 * @author Curt
 */
final class InvalidMethodParser
    extends AbstractParser
{

    @Override
    public boolean canParseTokens(Tokens tokens) {
        return tokens.hasNext();
    }

    public Method parse(Tokens tokens) {
        if (!Identifier.isValid(methodName(tokens))) {
            return method(tokens,methodName(tokens),INVALID_METHOD_NAME);
        }
        if (methodBody(tokens).length()==0) {
            return method(tokens,tokens.toString(),MALFORMED_METHOD);
        }
        if (methodParams(tokens).length()!=0) {
            return method(tokens,methodParams(tokens),INVALID_METHOD_PARAMS);
        }
        return method(tokens,methodBody(tokens),INVALID_METHOD_BODY);
    }

    Method method(Tokens tokens, String errorSource,SyntaxError.Type type) {
        String methodSource = tokens.toString();
        String methodName = methodName(tokens);
        consumeAll(tokens);
        return new Method(methodName,new SyntaxError(methodSource,errorSource,type));
    }
    
    void consumeAll(Tokens tokens) {
        while(tokens.hasNext()) {
            tokens.next();
        }
    }
    
    String methodName(Tokens tokens) {
        StringBuilder out = new StringBuilder();
        for (String token : tokens.toStrings()) {
            if (isOneOf(token,"(",")","{","}")) {
                break;
            }
            out.append(token);
        }
        return out.toString().trim();
    }

    String methodParams(Tokens tokens) {
        StringBuilder out = new StringBuilder();
        boolean started = false;
        for (String token : tokens.toStrings()) {
            if (started) {
                out.append(token + " ");
            }
            if (token.equals("(")) {
                started = true;
                out.append("( ");
            }
            if (isOneOf(token,")","{")) {
                break;
            }
        }
        return out.toString().trim();
    }

    String methodBody(Tokens tokens) {
        StringBuilder out = new StringBuilder();
        boolean started = false;
        for (String token : tokens.toStrings()) {
            if (started) {
                out.append(token + " ");
            }
            if (token.equals("{")) {
                started = true;
                out.append("{ ");
            }
        }
        return out.toString().trim();
    }
    
    boolean isOneOf(String string, String... choices) {
        for (String choice : choices) {
            if (string.equals(choice)) {
                return true;
            }
        }
        return false;
    }
}
