package hash;

/**
 *
 * @author Curt
 */
public final class Ternary
    extends Expression
{

    static final class Parser
        implements IParser
    {
        
        public Ternary parse(Tokens tokens) {
            tokens.verifyNextIs("(");
            Expression.Parser expressions = new Expression.Parser();
            Expression condition = expressions.parse(tokens);
            tokens.verifyNextIs(")");
            tokens.verifyNextIs("?");
            Expression pass = expressions.parse(tokens);
            tokens.verifyNextIs(":");
            Expression fail = expressions.parse(tokens);
            return new Ternary(condition,pass,fail);
        }    

        public boolean canParse(Tokens tokens) {
            Tokens copy = tokens.copy();
            if (!copy.nextIs("("))           { return false; }
            Expression.Parser expressions = new Expression.Parser();
            if (!expressions.canParse(copy)) { return false; }
            expressions.parse(copy);
            if (!copy.nextIs(")"))           { return false; }
            if (!copy.nextIs("?"))           { return false; }
            if (!expressions.canParse(copy)) { return false; }
            expressions.parse(copy);
            if (!copy.nextIs(":"))           { return false; }
            return expressions.canParse(copy);
        }
    }
    
    final Expression condition;
    final Expression pass;
    final Expression fail;
    
    Ternary(Expression condition, Expression pass, Expression fail) {
        this.condition = condition;
        this.pass = pass;
        this.fail = fail;
    }
    
    @Override
    public int hashCode() {
        return condition.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Ternary that = (Ternary) o;
        return condition.equals(that.condition) &&
               pass.equals(that.pass) &&
               fail.equals(that.fail);
    }

    @Override
    public String toString() {
        return condition.toString();
    }
}
