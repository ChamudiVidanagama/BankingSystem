package bank;

public class Customer extends Thread {
    protected MyCurrentAccount myCurrentAccount;
    protected final String CUSTOMERID;
    Statement statement;

    public Customer( String customerId, String name, ThreadGroup group, MyCurrentAccount myCurrentAccount){
        super(group, name);


        this.myCurrentAccount = myCurrentAccount;
        CUSTOMERID = customerId;

    }

    public int amountGenerator(){
        int amount = (int)(100000 + (Math.random() * (200000 - 100000)));
        return amount;
    }

    public int randomSleepTimeGenerator(){
        int sleepTime = (int)(1000 + (Math.random() * (2000 - 1000)));
        return sleepTime;
    }

    public Statement getStatement(){
         return statement;
    }

}
