public class MyClass {
    public static void main(String args[]) {
        System.out.println("Banking Program!");

        Account myChecking = new CheckingAccount("Tung", 100);
        Account mySaving = new SavingAccount("Tung", 100.0);

        System.out.println(myChecking.getmOwner() + " has " + myChecking.getmBalance() + " in the Checking Account!");
        System.out.println(SavingAccount.getmOwner() + " has " + SavingAccount.getmBalance() + " in the Saving Account!");

        myChecking.withdraw(150);

        myChecking.tell();

        mySaving.deposite(100);

        myChecking.tell();

        mySaving.withdraw(150);

        myChecking.tell();

        mySaving.withdraw(5);

        myChecking.tell();

        mySaving.withdraw(5);

        myChecking.tell();

        mySaving.withdraw(5);

        myChecking.tell();

        mySaving.withdraw(5);

        myChecking.tell();

        mySaving.withdraw(5);

        myChecking.tell();

        mySaving.withdraw(5);

        myChecking.tell();
        mySaving.withdraw(5);
        myChecking.tell();
        mySaving.withdraw(5);
        myChecking.tell();
        mySaving.withdraw(5);
        myChecking.tell();
        mySaving.withdraw(5);
        myChecking.tell();
        mySaving.withdraw(5);
        myChecking.tell();
        mySaving.withdraw(5);
        myChecking.tell();



    }
}
