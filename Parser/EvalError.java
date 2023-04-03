package ProjectOOP11.Parser;

public class EvalError extends Error{
    public EvalError(String msg){
        super(msg);
    }
    public static class LeftOverToken extends EvalError{
        public LeftOverToken(String message) {
            super("Token is not null : " + message);
        }
    }
}