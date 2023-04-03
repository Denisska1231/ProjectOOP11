package ProjectOOP11.Parser;

public class EvalError extends Error{
    public EvalError(String msg){
        super(msg);
    }
    public static class CommandHasLeftoverToken extends EvalError{
        public CommandHasLeftoverToken(String message) {
            super("Token is not null : " + message);
        }
    }
}