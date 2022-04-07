package bank;

import utils.Enums;

public class MyCurrentAccount implements BankAccount{
    private int balance = 500000;
    private final String accountHolder;
    private final int accountNumber;
    private ThreadGroup humans;
    Statement statement;


//    public void setStatement(Statement statement) {
//        this.statement = statement;
//    }

    public MyCurrentAccount(String accountHolder, int accountNumber){
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        statement = new Statement(accountHolder,accountNumber);
    }

    @Override
    public int getBalance() {
        return this.balance;
    }

    @Override
    public int getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public String getAccountHolder() {
        return this.accountHolder;
    }

    @Override
    public synchronized void deposit(Transaction t) {
        utils.LoggerProcess.logger("Start money deposit", Thread.currentThread().getStackTrace()[2].getClassName().toString(), Enums.MessageStatus.DEFAULT);
        this.balance += t.getAmount();
        utils.LoggerProcess.logger("Money successfully deposited to the bank account", Thread.currentThread().getStackTrace()[2].getClassName().toString(), Enums.MessageStatus.DEFAULT);
    }

    @Override
    public synchronized void withdrawal(Transaction t) {
        utils.LoggerProcess.logger("Before withdrawal", Thread.currentThread().getStackTrace()[2].getClassName().toString(), Enums.MessageStatus.DEFAULT);

        while (t.getAmount() > getBalance()) {
            try {
                utils.LoggerProcess.logger("Waiting to withdraw" , Thread.currentThread().getStackTrace()[2].getClassName().toString(), Enums.MessageStatus.DEFAULT);
                wait(); // Insufficient balance
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }

        if (t.getAmount() <= getBalance()){
            this.balance -= t.getAmount();
            utils.LoggerProcess.logger("Withdrawal Successful", Thread.currentThread().getStackTrace()[2].getClassName().toString(), Enums.MessageStatus.DEFAULT);
        }
        utils.LoggerProcess.logger("After withdrawal", Thread.currentThread().getStackTrace()[2].getClassName().toString(), Enums.MessageStatus.DEFAULT);

        notifyAll();
    }

    @Override
    public boolean isOverdrawn() {
        return false;
    }

    @Override
    public void printStatement(Statement statement) {
        statement.printall();
    }

    public void printheaders(){
        System.out.println( ) ;

        System.out.println( "Final Statement for "  +  accountHolder  +
                "'s MyCurrentAccount: "    +  accountNumber    ) ;

        System.out.println( "================================================" ) ;

        System.out.format( "%1$-20s %2$10s  %3$13s", "Customer", "Amount", "Balance" ) ;
        System.out.println() ;

        System.out.println( "================================================" ) ;


    }
}
