public class Account {
    protected static double mBalance;
    protected static double mTotalFee;
    protected int turm = 1;

    protected static String mOwner;

    public Account(String owner, double balance) {
        mOwner = owner;
        mBalance = balance;
    }

    public static double getmBalance() {return mBalance;}
    public static double getmTotalFee() {return mTotalFee;}
    static String getmOwner() {return mOwner;}

    public void deposite(double amount){
        if (amount <= 0){
            System.out.println("A negative amount is not allowed for deposite!");
        }
        else {
            mBalance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("A negative amount is not allowed for withdraw!");
        }
        else
            mBalance -= amount;

    }

    public void tell() {
        System.out.println(mOwner + " has " + this.getmBalance() + " in the bank. Total fee!" + this.getmTotalFee());

    }


}
