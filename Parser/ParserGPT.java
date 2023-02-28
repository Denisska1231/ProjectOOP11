import java.util.*;

public class ParserGPT {
    private List<String> tokens;
    private int currentTokenIndex;
    private Map<String, Long> symbolTable;

    public ParserGPT(List<String> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
        this.symbolTable = new HashMap<>();
    }
    private boolean match(String expected) {
        if (lexer.getCurrentToken().equals(expected)) {
            lexer.advance();
            return true;
        }
        return false;
    }

    public void parse() {
        while (currentTokenIndex < tokens.size()) {
            Statement statement = parseStatement();
            statement.execute(symbolTable);
        }
    }

    private Statement parseStatement() {
        if (match("done")) {
            return new DoneCommand();
        } else if (match("relocate")) {
            return new RelocateCommand();
        } else if (match("move")) {
            Direction direction = parseDirection();
            return new MoveCommand(direction);
        } else if (match("shoot")) {
            Direction direction = parseDirection();
            Expression expression = parseExpression();
            return new AttackCommand(direction, expression);
        } else if (match("collect")) {
            Expression expression = parseExpression();
            return new CollectCommand(expression);
        } else if (match("invest")) {
            Expression expression = parseExpression();
            return new InvestCommand(expression);
        } else if (match("{")) {
            List<Statement> statements = new ArrayList<>();
            while (!match("}")) {
                Statement statement = parseStatement();
                statements.add(statement);
            }
            return new BlockStatement(statements);
        } else if (match("if")) {
            Expression expression = parseExpression();
            consume("then");
            Statement thenStatement = parseStatement();
            consume("else");
            Statement elseStatement = parseStatement();
            return new IfStatement(expression, thenStatement, elseStatement);
        } else if (match("while")) {
            Expression expression = parseExpression();
            Statement statement = parseStatement();
            return new WhileStatement(expression, statement);
        } else if (matchIdentifier()) {
            String identifier = previousToken();
            consume("=");
            Expression expression = parseExpression();
            return new AssignmentStatement(identifier, expression);
        } else {
            throw new RuntimeException("Invalid statement: " + currentToken());
        }
    }

    private Direction parseDirection() {
        if (match("up")) {
            if (match("left")) {
                return Direction.UPLEFT;
            } else if (match("right")) {
                return Direction.UPRIGHT;
            } else {
                return Direction.UP;
            }
        } else if (match("down")) {
            if (match("left")) {
                return Direction.DOWNLEFT;
            } else if (match("right")) {
                return Direction.DOWNRIGHT;
            } else {
                return Direction.DOWN;
            }
        } else {
            throw new RuntimeException("Invalid direction: " + currentToken());
        }
    }

    private Expression parseExpression() {
        Expression expression = parseTerm();

        while (match("+") || match("-")) {
            String operator = previousToken();
            Expression nextTerm = parseTerm();
            expression = new BinaryExpression(expression, operator, nextTerm);
        }

        return expression;
    }

    private Expression parse
