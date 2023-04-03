package ProjectOOP11.GameState;

public interface Region { Player getOwner();
    void OwnerUpdate(Player Owner);
    long Deposit();
    void DepositUpdate(long money);
    int Location();
    int Rows();
    int Cols();
    void AddAddress(int row, int col);
}
