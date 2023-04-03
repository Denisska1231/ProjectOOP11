package ProjectOOP11.GameState;

import java.util.Map;

public interface Player {
    String getName();
    long Budget();
    void BudgetUpdate(long money);
    int getCityCenterLocation();
    Region getCityCenter();
    Map<String, Long> getIdentifiers();
    void CityCenterUpdate(Region to);
}
