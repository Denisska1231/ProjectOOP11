package ProjectOOP11.Parser;

public class AssignState implements Node {
    private final String name;
    private final Expr expr;

    public AssignState(String name, Expr expr)  throws SyntaxError {
        if(!Identifier.isLegalName(name))
            throw new SyntaxError("Illegal identifier name : " + name);

        this.name = name;
        this.expr = expr;
    }
}