import java.util.*;
public class Parser {
    private final Tokenizer tkz;
    public Parser(Tokenizer tkz) {
        this.tkz = tkz;
    }


    public String parse() throws SyntaxError {
        try{
            String ans = Plan();
            if(tkz.hasNextToken()) throw new SyntaxError("token is not null : " + tkz.peek());
            return ans;
        }catch (SyntaxError e){
            throw new SyntaxError(e.getMessage());
        }
    }
    private String Plan() throws SyntaxError{
        try{
            String v = Statement();
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    private String Statement() throws SyntaxError{
        String v = "";
        try{
            if (tkz.hasNextToken() && (tkz.peek("Command"))){
                tkz.consume();
                v = Command();
            }
            if (tkz.hasNextToken() && (tkz.peek("BlockStatement"))){
                tkz.consume();
                v = BlockStatement();
            }
            if (tkz.hasNextToken() && (tkz.peek("IfStatement"))){
                tkz.consume();
                v = IfStatement();
            }
            if (tkz.hasNextToken() && (tkz.peek("WhileStatement"))){
                tkz.consume();
                v = WhileStatement();
            }
            return v;

        } catch (IllegalArgumentException | NoSuchElementException e){
            throw new SyntaxError(e.getMessage());
        }
    }

    private String Command() throws SyntaxError {
        String v = "";
        try {
            if (tkz.hasNextToken() && (tkz.peek("AssignmentStatement"))) {
                tkz.consume();
               v = AssignmentStatement();
            }
            if (tkz.hasNextToken() && (tkz.peek("ActionCommand"))) {
                tkz.consume();
                v = ActionCommand();
            }
            return v;
        } catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }

    private int AssignmentStatement() throws SyntaxError {
        int v = Expression();
        try {
        return v;

        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
        private String ActionCommand() throws SyntaxError{
            String v = "";
            try {
                if (tkz.hasNextToken() && (tkz.peek("shoot"))) {
                    v = shoot(Direction());

                }
                return v;

            }catch (IllegalArgumentException | NoSuchElementException e) {
                throw new SyntaxError(e.getMessage());
            }
        }


    private String MoveCommand() throws SyntaxError{
        String v = Direction();
        try{
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
    private String RegionCommand() throws SyntaxError{
        String v = "";
        try{
            if (tkz.hasNextToken() && (tkz.peek("invest"))) {
                v = invest(Expression());
            }
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
    private boolean AttackCommand() throws SyntaxError{
        boolean v = false;
        try{
            if (tkz.hasNextToken() && (tkz.peek("shoot"))) {
                tkz.consume();
                v = shoot(Direction());
            }
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
    private String Direction() throws SyntaxError {
        String v ="";
        try{
            if (tkz.hasNextToken() && (tkz.peek("up"))) {
                tkz.consume();
                v = Dir.up();
            }
            if (tkz.hasNextToken() && (tkz.peek("down"))) {
                tkz.consume();
                v = Dir.down();
            }
            if (tkz.hasNextToken() && (tkz.peek("upleft"))) {
                tkz.consume();
                v = Dir.upleft();
            }
            if (tkz.hasNextToken() && (tkz.peek("upright"))) {
                tkz.consume();
                v = Dir.upright();
            }
            if (tkz.hasNextToken() && (tkz.peek("downleft"))) {
                tkz.consume();
                v = Dir.downleft();
            }
            if (tkz.hasNextToken() && (tkz.peek("downright"))) {
                tkz.consume();
                v = Dir.downright();
            }
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
    private String BlockStatement() throws SyntaxError{
        String v = Statement();
        try{
            tkz.consume();
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
    private String IfStatement() throws SyntaxError{
        String v = "";
        try{
            if(Expression()){
                v = Statement();
            }else{
                v = Statement();
            }
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
    private String WhileStatement() throws SyntaxError{
        String v = "";
        try{
            while (Expression()){
                v = Statement();
            }
            return v;
        }catch (IllegalArgumentException | NoSuchElementException e) {
            throw new SyntaxError(e.getMessage());
        }
    }
    private int Expression()throws SyntaxError{
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
    private String shoot(String direction) {
        String dir ;
        if (direction = "up") {
            return "up";
        }
    }
}
