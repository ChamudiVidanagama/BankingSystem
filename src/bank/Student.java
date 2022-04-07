package bank;

import java.util.stream.IntStream;

public class Student extends Customer{

    private String name;
    private String customerId;
    private MyCurrentAccount myCurrentAccount;
    Statement statement;

    public Student(String customerId, String name, ThreadGroup threadGroup, MyCurrentAccount myCurrentAccount){
        super(customerId, name, threadGroup, myCurrentAccount);
        this.name = name;
        this.customerId = customerId;
        this.myCurrentAccount = myCurrentAccount;
        statement = new Statement(myCurrentAccount.getAccountHolder(), myCurrentAccount.getAccountNumber());
    }

    @Override
    public void run(){

        IntStream.rangeClosed(1, 4).forEach(transactionId -> {
            int withdrawalAmount = amountGenerator();
            Transaction studentWithdrawal = new Transaction(customerId, withdrawalAmount) ;
            myCurrentAccount.withdrawal( studentWithdrawal );
            statement.addTransaction(customerId,withdrawalAmount,myCurrentAccount.getBalance());

            try {
                sleep(randomSleepTimeGenerator());
            } catch (InterruptedException exception) {
                System.out.println(exception);
            }

        });

        IntStream.rangeClosed(1, 2).forEach(transactionId -> {
            int depositAmount = amountGenerator();
            Transaction studentDeposit = new Transaction(customerId, depositAmount) ;
            myCurrentAccount.deposit( studentDeposit ) ;
            statement.addTransaction(customerId,depositAmount,myCurrentAccount.getBalance());

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
