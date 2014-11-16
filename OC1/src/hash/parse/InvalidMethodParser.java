package hash.parse;

import hash.Identifier;
import hash.Method;
import hash.SyntaxError;
import static hash.SyntaxError.Type.*;
import hash.lex.Tokens;

/**
 * For parsing methods with syntax errors into syntax errors.
 * @author Curt
 */
final class InvalidMethodParser
    extends AbstractParser
{

    @Override
    public boolean canParseTokens(Tokens tokens) {
        return true;
    }

    public Method parse(Tokens tokens) {
        String methodSource = tokens.toString();
        if (!Identifier.isValid(methodName(tokens))) {
            return new Method(methodName(tokens),new SyntaxError(methodSource,methodName(tokens),INVALID_METHOD_NAME));
        }
        if (methodBody(tokens).length()==0) {
            return new Method(tokens.peek(),new SyntaxError(methodSource,methodSource,MALFORMED_METHOD));
        }
        if (methodParams(tokens).length()!=0) {
            return new Method(tokens.peek(),new SyntaxError(methodSource,methodParams(tokens),INVALID_METHOD_PARAMS));
        }
        return new Method(tokens.peek(),new SyntaxError(methodSource,methodBody(tokens),INVALID_METHOD_BODY));
    }

    String methodName(Tokens tokens) {
        StringBuilder out = new StringBuilder();
        for (String token : tokens.toStrings()) {
            if (token.equals("(") || tokens.equals(")") ||
                token.equals("{") || token.equals("}")) {
                break;
            }
            out.append(token);
        }
        return out.toString();
    }

    String methodParams(Tokens tokens) {
        StringBuilder out = new StringBuilder();
        boolean started = false;
        for (String token : tokens.toStrings()) {
            if (started) {
                out.append(token);
            }
            if (token.equals("(")) {
                started = true;
                out.append("(");
            }
            if (token.equals(")")) {
                break;
            }
        }
        return out.toString();
    }

    String methodBody(Tokens tokens) {
        StringBuilder out = new StringBuilder();
        boolean started = false;
        for (String token : tokens.toStrings()) {
            if (started) {
                out.append(token);
            }
            if (token.equals("{")) {
                started = true;
                out.append("{");
            }
        }
        return out.toString();
    }
}
