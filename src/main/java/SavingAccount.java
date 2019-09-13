public class SavingAccount extends Account {
    public static double BANK_FEE = 5.0;
    public static double WAIVE_LINE = 100.0;
    public int times = 5;

    public SavingAccount(String owner, double balance) {
        super(owner, balance);
    }

    public void deposite(double amount) {
        super.deposite(amount);

        if (amount < WAIVE_LINE) {
            mTotalFee += BANK_FEE;
        }

    }

    public void withdraw(double amount) {

        if (times == 0) {

            System.out.println("Maximum withdraw limit reached!");
            return;
        }

           if (amount <= 0) {
                System.out.println("A negative amount is not allowed for withdraw!");
            }
           else if (mBalance < amount) {
                System.out.println("Balance is too low for withdraw!");
            }
           else {
                mBalance -= amount;
               times--;
            }


        }

}


