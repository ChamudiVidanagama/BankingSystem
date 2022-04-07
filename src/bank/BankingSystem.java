package bank;

public class BankingSystem {
    public static void main(String[] args) {
        // declaring and initializing the two thread groups humanGroup and companyGroup
        ThreadGroup humanGroup = new ThreadGroup("Human Thread Group");
        ThreadGroup companyGroup = new ThreadGroup("Company Thread Group");

        MyCurrentAccount myCurrentAccount = new MyCurrentAccount("Jin",12345);

        Customer student = new Student("1","Chamudi", humanGroup, myCurrentAccount);
        Customer grandma = new Grandmother("2","Rose", humanGroup, myCurrentAccount);
        Customer loanCompany = new LoanCompany("3","Loan Shark", companyGroup, myCurrentAccount);
        Customer university = new University("4","IIT", companyGroup, myCurrentAccount);

        student.start();
        grandma.start();
        loanCompany.start();
        university.start();

        // wait for all the threads to complete
        try {
            student.join();
            grandma.join();
            loanCompany.join();
            university.join();
        } catch (InterruptedException exception) {
            System.out.println(exception);
        }

        myCurrentAccount.printheaders();
        myCurrentAccount.printStatement(student.getStatement());
        myCurrentAccount.printStatement(grandma.getStatement());
        myCurrentAccount.printStatement(loanCompany.getStatement());
        myCurrentAccount.printStatement(university.getStatement());



    }
}
