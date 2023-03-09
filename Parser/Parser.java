import java.util.*;
import java.util.ArrayList;
public class Parser {
    private Tokenizer tkz;
    List<String> command = List.of("done", "relocate", "move", "invest", "collect", "shoot");
    List<String> specialVariables = List.of("if", "while", "done", "relocate", "move", "invest", "shoot", "up", "down", "upleft", "upright", "downleft", "downright", "if", "while", "then", "else", "opponent", "nearby", "rows", "cols", "currow", "curcol", "budget", "deposit", "int", "maxdeposit", "random");
    List<String> reservedWords = List.of("collect","done","down",
            "downleft","downright","else","if","invest","move","nearby","opponent",
            "relocate","shoot","then","up","upleft","upright","while");

    public Parser() {
    }

    private Node Plan() throws SyntaxError{
        Node v = Statement();
        return v;
    }

    private Node Statement() throws SyntaxError{
        Node v = Command();
            if (tkz.hasNextToken() && (tkz.peek("{"))){
                tkz.consume();
                v = BlockStatement();
            }
            if (tkz.hasNextToken() && (tkz.peek("if"))){
                tkz.consume();
                v = IfStatement();
            }
            if (tkz.hasNextToken() && (tkz.peek("while"))){
                tkz.consume();
                v = WhileStatement();
            }
            return v;
    }

    private Node Command() throws SyntaxError {
        if(command.contains(tkz.peek())){
            return ActionCommand();
        }
        return AssignmentStatement();
    }

    private Node AssignmentStatement() throws SyntaxError {
        String identifier = tkz.consume();
        long expr = Expression();
        if (tkz.peek("=")) {
            tkz.consume();
        }
        return (Node) new AssignStatement(identifier, expr);
    }
    private Node ActionCommand() throws SyntaxError{
        Node v = null;
            if (tkz.hasNextToken() && (tkz.peek("done"))) {
                v = Done();
            }
            if (tkz.hasNextToken() && (tkz.peek("relocate"))) {
                v = Relocate();
            }
            if (tkz.hasNextToken() && (tkz.peek("move"))) {
                v = Move();
            }
            if (tkz.hasNextToken() && (tkz.peek("invest"))) {
            v = Invest();
            }
            if (tkz.hasNextToken() && (tkz.peek("collect"))) {
                v = Collect();
            }
            if (tkz.hasNextToken() && (tkz.peek("shoot"))) {
                v = Shoot();
            }

                return v;


    }
    private Node Done(){
        return null;
    }
    private Node Relocate(){
        return null;
    }
    public Node Shoot() {
        String shootdir = tkz.consume();
        if (Objects.equals(shootdir, "up")) {
            Dir.shootup();
        }
        if (Objects.equals(shootdir, "down")) {
            Dir.shootdown();
        }
        if (Objects.equals(shootdir, "up")) {
            Dir.shootupleft();
        }
        if (Objects.equals(shootdir, "upright")) {
            Dir.shootupright();
        }
        if (Objects.equals(shootdir, "downleft")) {
            Dir.shootdownleft();
        }
        if (Objects.equals(shootdir, "downright")) {
            Dir.shootdownright();
        }
        return null;
    }
    private Node Move() throws SyntaxError{
       String movedir = tkz.consume();
        if (Objects.equals(movedir, "up")) {
            Dir.moveup();
        }
        if (Objects.equals(movedir, "down")) {
            Dir.movedown();
        }
        if (Objects.equals(movedir, "up")) {
            Dir.moveupleft();
        }
        if (Objects.equals(movedir, "upright")) {
            Dir.moveupright();
        }
        if (Objects.equals(movedir, "downleft")) {
            Dir.movedownleft();
        }
        if (Objects.equals(movedir, "downright")) {
            Dir.movedownright();
        }
        return null;
    }

    private Node Invest(){
        long expr = Expression();
        return (Node) new Invest(expr);
    }
    private Node Collect(){
        long expr = Expression();
        return (Node) new Collect(expr);
    }
    private Node BlockStatement() throws SyntaxError{
        Node v = Statement();
        tkz.consume("}");
        return v;
    }
    private Node IfStatement() throws SyntaxError{
        tkz.consume("(");
        long expr = Expression();
        tkz.consume(")");
        tkz.consume("then");
        Node Then = Statement();
        tkz.consume("else");
        Node Else = Statement();
        return (Node) new If( expr, Then, Else );
    }
    private Node WhileStatement() throws SyntaxError{
        tkz.consume("(");
        long expr = Expression();
        tkz.consume(")");
        Node Do = Statement();
        return (Node) new While( expr, Do );
    }
    private long Expression()throws SyntaxError{
        int v = Term();
        try{
            while (tkz.hasNextToken() && (tkz.peek("+") || tkz.peek("-"))){
                if(tkz.peek("+")){
                    tkz.consume();
                    v += Term();
                } else if (tkz.peek("-")) {
                    tkz.consume();
                    v -= Term();
                }
            }
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
    private int Term() throws SyntaxError{
        try{
            int v = Factor();
            while (tkz.hasNextToken() &&(tkz.peek("*") || tkz.peek("/") || tkz.peek("%"))){
                if(tkz.peek("*")){
                    tkz.consume();
                    v *= Factor();
                } else if (tkz.peek("/")) {
                    tkz.consume();
                    v /= Factor();
                } else if (tkz.peek("%")) {
                    tkz.consume();
                    v %= Factor();
                }
            }
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private int Factor()throws SyntaxError{
        int v = Power();
        try{
            while (tkz.hasNextToken() && (tkz.peek("^"))){
                if(tkz.peek("^")){
                    tkz.consume();
                    v ^= Term();
                }
            }
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
    private int Power() throws SyntaxError{
        int v = InfoExpression();
        try{
            if(Character.isDigit(tkz.peek().charAt(0))){
                return  Integer.parseInt(tkz.consume());
            }
            if(tkz.hasNextToken() && (tkz.peek("identifier"))){
                tkz.consume();
                v = identifier();
            }
            while (tkz.hasNextToken() && (tkz.peek("(") || tkz.peek(")"))){
                    tkz.consume("(");
                    v = Expression();
                    tkz.consume(")");
                }
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
    private String InfoExpression() throws SyntaxError{
        String v = "";
        try{
            if(tkz.hasNextToken() && (tkz.peek("opponent"))){
                v = opponent();
            }
            if(tkz.hasNextToken() && (tkz.peek("opponent"))){
                v = nearby(Direction());
            }
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
}
