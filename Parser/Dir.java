package ProjectOOP11.Parser;

public class Dir implements Node {
    public static Dir Down;
    public String expr;
    public String movedir;

    public Dir(String movedir) {
        this.movedir = movedir;
    }

    public String Direc() {
        return movedir;
    }
    static String shootup() {
        return "up";
    }
    static String shootdown() {
        return "down";
    }
    static String shootupleft() {
        return "upleft";
    }
    static String shootupright() {
        return "up";
    }
    static String shootdownleft() {
        return "downleft";
    }
    static String shootdownright() {
        return "downright";
    }
    static String moveup() {
        return "up";
    }
    static String movedown() {
        return "down";
    }
    static String moveupleft() {
        return "upleft";
    }
    static String moveupright() {
        return "up";
    }
    static String movedownleft() {
        return "downleft";
    }
    static String movedownright() {
        return "downright";
    }
}