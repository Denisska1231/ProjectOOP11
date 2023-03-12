import java.util.Map;

interface Expr {
    long eval(Map<String, Long> bindings) throws EvalError;
}