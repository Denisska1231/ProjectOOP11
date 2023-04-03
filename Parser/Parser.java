package ProjectOOP11.Parser;

import java.util.*;
import ProjectOOP11.GameState.Game;

public class Parser {
    private final Tokenizer tkz;
    List<String> command = List.of("done", "relocate", "move", "invest", "collect", "shoot");
    List<String> specialVariables = List.of("if", "while", "done", "relocate", "move", "invest", "shoot", "up", "down", "upleft", "upright", "downleft", "downright", "if", "while", "then", "else", "opponent", "nearby", "rows", "cols", "currow", "curcol", "budget", "deposit", "int", "maxdeposit", "random");
    List<String> reservedWords = List.of("collect","done","down",
            "downleft","downright","else","if","invest","move","nearby","opponent",
            "relocate","shoot","then","up","upleft","upright","while");
    public Parser(Tokenizer tkz) {
        this.tkz = tkz;
    }
    public List<Node> parse() {
        List<Node> v = Plan();
        if(tkz.hasNextToken()) throw new EvalError.LeftOverToken(tkz.peek());
        return v;
    }

    List<Node> Plan() throws SyntaxError{
        List<Node> plan = new ArrayList<>();
        plan.add(Statement());
        Statements(plan);
        return plan;
    }
    protected void Statements(List<Node> list) {
        while(tkz.hasNextToken() && !tkz.peek("}")) {
            list.add(Statement());
        }
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

    private Node AssignmentStatement() throws SyntaxError  {
        String identifier = tkz.consume();
        if (tkz.peek("=")) {
            tkz.consume();
        }
        Expr expr = Expression();
        return new AssignState(identifier, expr);
    }
    Node ActionCommand() throws SyntaxError{
        Node v = null;
            if (tkz.hasNextToken() && (tkz.peek("done"))) {
                v = new Done();
            }
            if (tkz.hasNextToken() && (tkz.peek("relocate"))) {
                v = new Relocate();
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
    private static class Done implements Node{
        public boolean evaluate(Game game) {
            return true;
        }
    }
    private static class Relocate implements Node{
        public boolean evaluate(Game game) {
            game.relocate();
            return true;
        }
    }
    public Node Shoot() {
        String shootdir = tkz.consume();
        if (Objects.equals(shootdir, "up")) {
            shootdir = Dir.shootup();
        }
        if (Objects.equals(shootdir, "down")) {
            shootdir = Dir.shootdown();
        }
        if (Objects.equals(shootdir, "upleft")) {
            shootdir = Dir.shootupleft();
        }
        if (Objects.equals(shootdir, "upright")) {
            shootdir = Dir.shootupright();
        }
        if (Objects.equals(shootdir, "downleft")) {
            shootdir = Dir.shootdownleft();
        }
        if (Objects.equals(shootdir, "downright")) {
            shootdir = Dir.shootdownright();
        }
        return new Dir(shootdir);
    }
    public Node Move() throws SyntaxError{
        String movedir = tkz.consume();
        if (Objects.equals(movedir, "up")) {
            movedir = Dir.moveup();
        }
        if (Objects.equals(movedir, "down")) {
            movedir = Dir.movedown();
        }
        if (Objects.equals(movedir, "upleft")) {
            movedir = Dir.moveupleft();
        }
        if (Objects.equals(movedir, "upright")) {
            movedir = Dir.moveupright();
        }
        if (Objects.equals(movedir, "downleft")) {
            movedir = Dir.movedownleft();
        }
        if (Objects.equals(movedir, "downright")) {
            movedir = Dir.movedownright();
        }
        return new Dir(movedir);
    }
    private Direction Direction() {
        String direction = tkz.consume();
        System.out.println(direction);
        return switch (direction) {
            case "up" -> Direction.Up;
            case "down" -> Direction.Down;
            case "upleft" -> Direction.UpLeft;
            case "upright" -> Direction.UpRight;
            case "downleft" -> Direction.DownLeft;
            case "downright" -> Direction.DownRight;
            default -> null;
        };
    }

    private Node Invest() {
        Expr expr = Expression();
        return new Invest(expr);
    }
    private Node Collect(){
        Expr expr = Expression();
        return new Collect(expr);
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
        return new If( expr, Then, Else );
    }
    private Node WhileStatement() throws SyntaxError{
        tkz.consume("(");
        Expr expr = Expression();
        tkz.consume(")");
        Node Do = Statement();
        return new While( expr, Do );
    }
    Expr Expression()throws SyntaxError{
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
                return new Digit(Long.parseLong(tkz.consume()));
            }
            if(tkz.hasNextToken() && (tkz.peek("identifier"))){
                tkz.consume();
                v = new Identifier(tkz.consume());
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
                Info = new Opponent();
            }
            if(tkz.hasNextToken() && (tkz.peek("nearby"))){
                tkz.consume();
                Direction direction = Direction();
                return new Nearby(direction);
            }
        return Info;
    }
}
