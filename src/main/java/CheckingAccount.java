public class CheckingAccount extends Account{
    public static double OVERDRAFT_FEE = 35.0;

    public CheckingAccount(String owner, double balance) {
        super(owner, balance);
    }

    public void withdraw(double amount) {
        super.withdraw(amount);

        if (mBalance < 0) {
            mTotalFee += OVERDRAFT_FEE;
        }
    }
}
