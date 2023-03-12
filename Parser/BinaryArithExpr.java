import java.util.Map;

class BinaryArithExpr implements Expr {
    private final Expr left;
    private final Expr right;
    private final String op;

    public BinaryArithExpr(
            Expr left, String op, Expr right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public long eval(Map<String, Long> bindings) {
        long lv = left.eval(bindings);
        long rv = right.eval(bindings);
        if (op.equals("+")) return lv + rv;
        if (op.equals("-")) return lv - rv;
        if (op.equals("*")) return lv * rv;
        if (op.equals("/")) {
            if (rv == 0) {
                throw new ArithmeticException("Can't be calculate : " + lv + " / " + rv);
            }
            return lv / rv;
        }
        if (op.equals("%")) {
            if (rv == 0) {
                throw new ArithmeticException("Can't be calculate : " + lv + " % " + rv);
            }
            return lv % rv;
        }
        if (op.equals("^")) return (long) Math.pow(lv, rv);

        throw new EvalError("unknown op: " + op);
    }
}