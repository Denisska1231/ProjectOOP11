package ProjectOOP11.Parser;

import java.util.Map;

public class Digit implements Expr {
    private Long val;
    public Digit(Long val) {
        this.val = val;
    }

    @Override
    public long eval(Map<String, Long> bindings) throws EvalError {
        return val;
    }
}
