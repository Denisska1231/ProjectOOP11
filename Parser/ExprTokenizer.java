
import java.util.NoSuchElementException;
class ExprTokenizer implements Tokenizer {
    private String src, next;  private int pos;
    public ExprTokenizer(String src) {
        this.src = src;  pos = 0;
        computeNext();
    }
    public boolean hasNextToken()
    { return next != null; }
    public String peek() {
        if (!hasNextToken()) throw new
                NoSuchElementException("no more tokens");
        return next;
    }
    public String consume() {
        if (!hasNextToken()) throw new
                NoSuchElementException("no more tokens");
        String result = next;
        computeNext();
        return result;
    }
    private void computeNext() {
        StringBuilder s = new StringBuilder();
        while (pos < src.length() && Character.isWhitespace(src.charAt(pos)))
            pos++;  // ignore whitespace
        if (pos == src.length())
        { next = null;  return; }  // no more tokens
        char c = src.charAt(pos);
        if (Character.isLetter(c)) {  // start of Letter
            s.append(c);
            for (pos++; pos < src.length() &&
                    Character.isLetter(src.charAt(pos)); pos++)
                s.append(src.charAt(pos));
        }
        else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '(' || c == ')') {
            s.append(c);  pos++;
        }
        else throw new IllegalArgumentException("Illegal character: " + c);
        next = s.toString();
    }
    /** Returns true if
     *  the next token (if any) is s. */
    public boolean peek(String s) {
        if (!hasNextToken()) return false;
        return peek().equals(s);
    }

    /** Consumes the next token if it is s.
     *  Throws SyntaxError otherwise.
     *  effects: removes the next token
     *           from input stream if it is s
     */
    public void consume(String s)
            throws SyntaxError {
        if (peek(s))
            consume();
        else
            throw new SyntaxError(s + " expected");
    }
}