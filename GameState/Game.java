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
    long getRow();
    long getCol();
    long getCityCrewRow();
    long getCityCrewCol();
    long getBudget();
    long getDeposit();
    long getInterest();
    long getMaxDeposit();
    long getRandom();
    Player getCurrentPlayer();
    Player getPlayer1();
    Player getPlayer2();
    Player getWinner();
    String[][] getNameOwnerRegion();
    void sendPlan(String plan);
}
