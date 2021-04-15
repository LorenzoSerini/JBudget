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

package it.unicam.cs.pa.jbudget097855.modelvc;
import java.util.List;
import java.util.function.Predicate;

import it.unicam.cs.pa.jbudget097855.jbexception.TransactionException;

/**
 * This interface is implemented by the classes that are responsible for managing all the application data.
 * @author LorenzoSerini
 *
 */
public interface Ledger {
	/**
	 * Returns accounts list
	 * @return accounts list
	 */
	List<Account> getAccounts();
	
	/**
	 * Adds a transaction in ledger
	 * @param <code>t</code> The tag
	 */
	void addTransaction(Transaction t) throws TransactionException;
	
	/**
	 * Returns transactions list
	 * @return transactions list
	 */
	List<Transaction> getTransactions();
	
	/**
	 * Returns the list of transactions related to the predicate
	 * @param <code>p</code> The Predicate
	 * @return the movements list that respect the parameter
	 */
	List<Transaction> getTransactions(Predicate<Transaction> p);
	
	/**
	 * Returns tags list
	 * @return tags list
	 */
	List<Tag> getTags();
	
	/**
	 * Adds an account
	 * @param type The AccountType 
	 * @param name The String containing the name
	 * @param description  The String containing the description
	 * @param openingBalance The Account openingBalance
	 * @return the account
	 */
	Account addAccount(AccountType type, String name, String description, double openingBalance);
	
	/**
	 * Adds a tag
	 * @param name The String containing the name
	 * @param description The String containing the description
	 * @return the tag
	 */
	Tag addTag(String name, String description);
	
	/**
	 * Removes the tag
	 * @param t The tag
	 */
	void removeTag(Tag t);
	
	/**
	 * Adds a scheduled transaction
	 * @param s The ScheduledTransaction
	 */
	void addScheduledTransaction(ScheduledTransaction s);
	
	/**
	 * Return the scheduled transaction list
	 * @return scheduled transaction list
	 */
	List<Scheduled> getSchedulers();
	
	/**
	 * Schedules transactions with past dates and deletes them from the schedulers list
	 */
	void schedule() throws TransactionException;
	 
	
}
