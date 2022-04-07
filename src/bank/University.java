package bank;

import java.util.stream.IntStream;

public class University extends Customer {
    private String name;
    private String customerId;
    private MyCurrentAccount myCurrentAccount;
    Statement statement;

    public University(String customerId, String name, ThreadGroup threadGroup, MyCurrentAccount myCurrentAccount){
        super(customerId, name, threadGroup, myCurrentAccount);
        this.name = name;
        this.customerId = customerId;
        this.myCurrentAccount = myCurrentAccount;
        statement = new Statement(myCurrentAccount.getAccountHolder(), myCurrentAccount.getAccountNumber());
    }

    @Override
    public void run(){
        IntStream.rangeClosed(1, 3).forEach(transactionId -> {
            int amount = amountGenerator();
            Transaction coursefeeWithdrawal = new Transaction(customerId, amount) ;
            myCurrentAccount.withdrawal( coursefeeWithdrawal ); ;
            statement.addTransaction(customerId,amount,myCurrentAccount.getBalance());

            try {
                sleep(randomSleepTimeGenerator());
            } catch (InterruptedException exception) {
                System.out.println(exception);
            }
        });

        statement.print();
    }

    public Statement getStatement() {
        return statement;
    }

}
