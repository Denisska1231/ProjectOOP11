package ProjectOOP11.Parser;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {
    Parser a = new Parser(new ExprTokenizer("up"));
    Parser d = new Parser(new ExprTokenizer("ss"));
    Parser b = new Parser(new ExprTokenizer("2^5^2"));
    Parser e = new Parser(new ExprTokenizer("(2^5)^2"));
    Parser f = new Parser(new ExprTokenizer("100/20"));
    Parser g = new Parser(new ExprTokenizer("999-888"));
    String con = "t = t + 1  \n" +
            "m = 0  \n" +
            "while (deposit) { \n" +
            "  if (deposit - 100)\n" +
            "  then collect (deposit / 4) \n" +
            "  else if (budget - 25) then invest 25\n" +
            "  else {}\n" +
            "  if (budget - 100) then {} else done  \n" +
            "  opponentLoc = opponent\n" +
            "  if (opponentLoc / 10 - 1)\n" +
            "  then  \n" +
            "    if (opponentLoc % 10 - 5) then move downleft\n" +
            "    else if (opponentLoc % 10 - 4) then move down\n" +
            "    else if (opponentLoc % 10 - 3) then move downright\n" +
            "    else if (opponentLoc % 10 - 2) then move up\n" +
            "    else if (opponentLoc % 10 - 1) then move downleft\n" +
            "    else move up\n" +
            "  else if (opponentLoc)\n" +
            "  then  \n" +
            "    if (opponentLoc % 10 - 5) then {\n" +
            "      cost = 10 ^ (nearby upleft % 100 + 1)\n" +
            "      if (budget - cost) then shoot upleft cost else {}\n" +
            "    }\n" +
            "    else if (opponentLoc % 10 - 4) then {\n" +
            "      cost = 10 ^ (nearby downleft % 100 + 1)\n" +
            "      if (budget - cost) then shoot downleft cost else {}\n" +
            "    }\n" +
            "    else if (opponentLoc % 10 - 3) then {\n" +
            "      cost = 10 ^ (nearby down % 100 + 1)\n" +
            "      if (budget - cost) then shoot down cost else {}\n" +
            "    }\n" +
            "    else if (opponentLoc % 10 - 2) then {\n" +
            "      cost = 10 ^ (nearby downright % 100 + 1)\n" +
            "      if (budget - cost) then shoot downright cost else {}\n" +
            "    }\n" +
            "    else if (opponentLoc % 10 - 1) then {\n" +
            "      cost = 10 ^ (nearby upright % 100 + 1)\n" +
            "      if (budget - cost) then shoot upright cost else {}\n" +
            "    }\n" +
            "    else {\n" +
            "      cost = 10 ^ (nearby up % 100 + 1)\n" +
            "      if (budget - cost) then shoot up cost else {}\n" +
            "    }\n" +
            "  else { \n" +
            "    dir = random % 6\n" +
            "    if (dir - 4) then move upleft\n" +
            "    else if (dir - 3) then move downleft\n" +
            "    else if (dir - 2) then move down\n" +
            "    else if (dir - 1) then move downright\n" +
            "    else if (dir) then move upright\n" +
            "    else move up\n" +
            "    m = m + 1\n" +
            "  }\n" +
            "}  \n" +
            "if (budget - 1) then invest 1 else {}\n";
    Parser c = new Parser(new ExprTokenizer(con));
    @Test
    void parse() {
        assertEquals("up", ((Dir)a.Move()).Direc()); //test move
        System.out.println(d.ActionCommand()); //test actioncommand
        assertEquals(33554432, b.Expression().eval(null)); //test factor
        assertEquals(1024, e.Expression().eval(null)); // test power
        assertEquals(5, f.Expression().eval(null)); // test term
        assertEquals(111, g.Expression().eval(null)); // test expression
        //c.parse(); // passed : Assignmentstate, WhileStatement
    }

}
