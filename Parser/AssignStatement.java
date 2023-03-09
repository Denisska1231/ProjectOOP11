import java.util.Map;

public class AssignStatement {
    private final String name;
    private final long expr;

    public AssignStatement(String name, long expr) throws SyntaxError {
        if(!Identifier.isLegalName(name))
            throw new SyntaxError("Illegal identifier name : " + name);

        this.name = name;
        this.expr = expr;
    }
}