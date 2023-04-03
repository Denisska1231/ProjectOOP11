package ProjectOOP11.GameState;

import ProjectOOP11.Parser.Direction;

import java.util.List;
import java.util.Map;

public interface Game {
    Map<String, Long> getIdentifiers();
    boolean attack(Direction direction, long money);
    boolean collect(long money);
    boolean invest(long money);
    boolean move(Direction direction);
    boolean relocate();
    long nearby(Direction direction);
    long opponent();
    List<Region> getTerritory();
    Region getRegion(int location);
    Region getCityCrew();
    long Rows();
    long Cols();
    long CurRow();
    long CurCol();
    long Budget();
    long Deposit();
    long Int();
    long MaxDeposit();
    long Random();
    Player getCurrentPlayer();
    Player getPlayer1();
    Player getPlayer2();
    Player getWinner();
    String[][] OwnedRegion();
    void sendConstruction(String plan);
}
