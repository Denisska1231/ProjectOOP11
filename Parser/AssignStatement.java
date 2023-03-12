package ProjectOOP11.Parser;

public class AssignStatement {
    private final String name;
    private final Expr expr;

    public AssignStatement(String name, Expr expr) throws SyntaxError {
        if(!Identifier.isLegalName(name))
            throw new SyntaxError("Illegal identifier name : " + name);

        this.name = name;
        this.expr = expr;
    }
}