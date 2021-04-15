/*******************************************************************************
 * MIT License
 * Copyright (c) 2020 Lorenzo Serini
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
/**
 * 
 */

package it.unicam.cs.pa.jbudget097855.mvcontroller;

import java.time.LocalDate;

import it.unicam.cs.pa.jbudget097855.jbexception.DateException;
import it.unicam.cs.pa.jbudget097855.jbexception.OpeningBalanceException;
import it.unicam.cs.pa.jbudget097855.jbexception.TransactionException;
import it.unicam.cs.pa.jbudget097855.modelvc.BudgetManager;
import it.unicam.cs.pa.jbudget097855.modelvc.Ledger;
/**
 * This interface is the application controller
 * @author LorenzoSerini
 *
 */

public interface Controller<T extends Ledger, B extends BudgetManager> {
	/**
	 * Process a tag
	 * @param name The tag name
	 * @param description The tag description
	 */
	public void processTag(String name, String description);
	
	/**
	 * Process an account
	 * @param type The account type
	 * @param name The account name
	 * @param description The account description
	 * @param openingBalance The account opening balance
	 * @throws OpeningBalanceException
	 */
	public void processAccount(String type, String name, String description, double openingBalance) throws OpeningBalanceException;

	/**
	 * Process a movement
	 * @param accountIndex
	 * @param type The account type
	 * @param description The account description
	 * @param amount The account amount
	 * @param tagIndex
	 */
	public void processMovement(int accountIndex, String type, String description, double amount, int...tagIndex);
	
	/**
	 * Process a transaction
	 * @param tagIndex
	 * @throws TransactionException
	 */
	public void processTransactionWithMovements(int...tagIndex) throws TransactionException;
	
	/**
	 * Set a value for a tag
	 * @param tagIndex
	 * @param expectedValue
	 */
	public void setExpectedBudget(int tagIndex, double expectedValue);
	
	/**
	 * Generate a report
	 */
	public void generateReport();
	
	/**
	 * Add a scheduled transaction
	 * @param date The scheduled date
	 * @param description The scheduled description
	 * @param transactionIndex 
	 * @throws DateException
	 */
	public void addScheduledTransaction(LocalDate date, String description, int...transactionIndex) throws DateException;
	
	/**
	 * Schedules transactions with past dates
	 */
	public void scheduleTransactions() throws TransactionException;
	
	/**
	 * Return a T ledger
	 * @return T
	 */
	T getLedger();
	
	/**
	 * Return a B budget manager
	 * @return B
	 */
	B getBudgetManager();

}
