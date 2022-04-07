/** *********************************************************************
 * File:      BankAccount.java  [interface]	
 * Author:    P. Howells	
 * Contents:  6SENG002W CW:  Banking System
 *            This provides the interface for a Bank MyCurrentAccount class.
 * Date:      23/10/21
 ************************************************************************ */
package bank;

public interface BankAccount
{
    int    getBalance( ) ;           // returns the current balance

    int    getAccountNumber( ) ;     // returns the MyCurrentAccount number

    String getAccountHolder( ) ;     // returns the MyCurrentAccount holder


    void deposit( Transaction t ) ;     // perform a deposit transaction on the bank account

    void withdrawal( Transaction t ) ;  // perform a withdrawal transaction on the bank account


    boolean isOverdrawn( ) ;         // returns true if overdrawn; false otherwise

    void printStatement(Statement statement ) ;         // prints out the transactions performed so far
}
