package ProjectOOP11.Parser;

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
    private void readText() {
        while(pos <src.length() && src.charAt(pos) != '\n') pos++;
    }
    private void computeNext() {
        String check = "+-*/(){}^=%";
        StringBuilder s = new StringBuilder();
        if(src == null) return;
        while (pos < src.length() && Character.isWhitespace(src.charAt(pos))) {
            if(src.charAt(pos) == '#') readText();
            else pos++;
        }
        if(pos == src.length()) {
            next = null;
            return;
        }
        char c = src.charAt(pos);
        if(Character.isDigit(c)) {
            while (pos < src.length() && Character.isDigit(src.charAt(pos))) {
                s.append(src.charAt(pos));
                pos++;
            }
        } else if (Character.isAlphabetic(c) || c == '_') {
            while (pos < src.length() && Character.isAlphabetic(src.charAt(pos))) {
                s.append(src.charAt(pos));
                pos++;
            }
        } else if (check.contains(Character.toString(c))) {
            s.append(src.charAt(pos));
            pos++;
        }else{
            throw new IllegalArgumentException("Illegal character: " + c);
        }
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