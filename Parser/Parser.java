package ProjectOOP11.Parser;

import java.util.*;

public class Parser {
    private Tokenizer tkz;
    List<String> command = List.of("done", "relocate", "move", "invest", "collect", "shoot");
    List<String> specialVariables = List.of("if", "while", "done", "relocate", "move", "invest", "shoot", "up", "down", "upleft", "upright", "downleft", "downright", "if", "while", "then", "else", "opponent", "nearby", "rows", "cols", "currow", "curcol", "budget", "deposit", "int", "maxdeposit", "random");
    List<String> reservedWords = List.of("collect","done","down",
            "downleft","downright","else","if","invest","move","nearby","opponent",
            "relocate","shoot","then","up","upleft","upright","while");
    public Parser(Tokenizer tkz) {
        this.tkz = tkz;
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
        Expr expr = Expression();
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
        Expr expr = Expression();
        return (Node) new Invest(expr);
    }
    private Node Collect(){
        Expr expr = Expression();
        return (Node) new Collect(expr);
    }
    private Node BlockStatement() throws SyntaxError{
        Node v = Statement();
        tkz.consume("}");
        return v;
    }
    private Node IfStatement() throws SyntaxError{
        tkz.consume("(");
       Expr expr = Expression();
        tkz.consume(")");
        tkz.consume("then");
        Node Then = Statement();
        tkz.consume("else");
        Node Else = Statement();
        return (Node) new If( expr, Then, Else );
    }
    private Node WhileStatement() throws SyntaxError{
        tkz.consume("(");
        Expr expr = Expression();
        tkz.consume(")");
        Node Do = Statement();
        return (Node) new While( expr, Do );
    }
    private Expr Expression()throws SyntaxError{
        Expr left = Term();
            while (tkz.hasNextToken() && (tkz.peek("+") || tkz.peek("-"))){
                if(tkz.peek("+")){
                    String op = tkz.consume();
                    Expr right = Term();
                    left = new BinaryArithExpr( left, op, right );
                } else if (tkz.peek("-")) {
                    String op = tkz.consume();
                    Expr right = Term();
                    left = new BinaryArithExpr( left, op, right );
                }
            }
            return left;
    }
    private Expr Term() throws SyntaxError{
        Expr left = Factor();
        while (tkz.hasNextToken() && (tkz.peek("*") || tkz.peek("/")) || tkz.peek("%")){
            if(tkz.peek("+")){
                String op = tkz.consume();
                Expr right = Term();
                left = new BinaryArithExpr( left, op, right );
            }
            if (tkz.peek("/")) {
                String op = tkz.consume();
                Expr right = Term();
                left = new BinaryArithExpr( left, op, right );
            }
            if (tkz.peek("%")) {
                String op = tkz.consume();
                Expr right = Term();
                left = new BinaryArithExpr( left, op, right );
            }
        }
        return left;
    }
    private Expr Factor()throws SyntaxError {
        Expr left = Power();
        while (tkz.hasNextToken() && (tkz.peek("^"))) {
            if (tkz.peek("^")) {
                String op = tkz.consume();
                Expr right = Term();
                left = new BinaryArithExpr(left, op, right);
            }
        }
        return left;
    }
    private Expr Power() throws SyntaxError{
        Expr v = InfoExpression();

            if(Character.isDigit(tkz.peek().charAt(0))){
                return (Expr) new Digit(Long.parseLong(tkz.consume()));
            }
            if(tkz.hasNextToken() && (tkz.peek("identifier"))){
                tkz.consume();
                v = (Expr) new Identifier(tkz.consume());
            }
            while (tkz.hasNextToken() && (tkz.peek("(") || tkz.peek(")"))){
                    tkz.consume("(");
                    v = Expression();
                    tkz.consume(")");
                }
            return v;
    }
    private Expr InfoExpression() throws SyntaxError{
        Expr Info = null;
            if(tkz.hasNextToken() && (tkz.peek("opponent"))){
                Info = (Expr) new Opponent();
            }
            if(tkz.hasNextToken() && (tkz.peek("opponent"))){
                Info = (Expr) new Nearby();
            }
        return Info;
    }
}
