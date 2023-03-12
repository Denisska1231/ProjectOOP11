package ProjectOOP11.Parser;

import java.util.Map;

public abstract class Statement {
    // Define abstract methods that all Statement subclasses must implement
    public abstract void execute(Map<String, Long> symbolTable);
    public abstract String toString();
}