package ProjectOOP11.Parser;

import ProjectOOP11.GameState.Game;

import java.util.Map;

public class Nearby implements Expr{
    private final Direction direction;

    public Nearby(Direction direction) {
        this.direction = direction;
    }
    @Override
    public long eval(Map<String, Long> bindings) throws EvalError {
        return 0;
    }
}
