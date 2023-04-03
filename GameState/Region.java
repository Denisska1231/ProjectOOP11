package ProjectOOP11.GameState;

public interface Region { Player getOwner();
    void updateOwner(Player owner);
    long getDeposit();
    void updateDeposit(long money);
    int getLocation();
    int getRow();
    int getCol();
    void AddAddress(int row, int col);
}
