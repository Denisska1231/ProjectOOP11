package ProjectOOP11.Parser;

import java.util.NoSuchElementException;

public interface Tokenizer {
    boolean hasNextToken();
    String peek() throws NoSuchElementException;
    String consume() throws NoSuchElementException;
    boolean peek(String s);
    void consume(String s);
}
