package ProjectOOP11.Parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Identifier implements Expression {
    private final String name;
    static List<String> reservedWords = List.of("collect","done","down",
            "downleft","downright","else","if","invest","move","nearby","opponent",
            "relocate","shoot","then","up","upleft","upright","while");
    public Identifier(String name) throws SyntaxError {
        if (!isLegalName(name))
            throw new SyntaxError("Illegal identifier name : " + name);
        this.name = name;
    }
    private static final Pattern pattern = Pattern.compile("[a-zA-Z_$][\\w$]*");
    public static boolean isLegalName(String name){
        Matcher matcher = pattern.matcher(name);

        boolean isEmpty = name.isEmpty();
        boolean isReserved = reservedWords.contains(name);
        boolean illegalGeneral = !matcher.matches();
        return !(isEmpty || isReserved || illegalGeneral);
    }
}