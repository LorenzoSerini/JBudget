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

import java.time.LocalDate;
import java.util.List;

/**
 * 
 * Indicates a transaction or a series of transactions scheduled on a specific date. 
 * @author LorenzoSerini
 *
 */
public interface Scheduled {
	/**
	 * Returns scheduled description
	 * @return description
	 */
	String getDescription();
	
	/**
	 * Returns transactions list
	 * @return transactions list
	 */
	List<Transaction> getTransactions();
	
	/**
	 * Returns the date
	 * @return date
	 */
	LocalDate getDate();
	
	/**
	 * If the set date is earlier than today return <code>true</code> else </code>false</code>
	 * @return true or false
	 */
	boolean isCompleted();
	
	/**
	 * Adds transaction
	 * @param t The Transaction
	 */
	void addTransaction(Transaction t);
	
	/**
	 * Removes the transactions
	 * @param t The transaction
	 */
	void removeTransaction(Transaction t);
	
	/**
	 * Set the scheduled description
	 * @param description The String containing the description
	 */
	void setDescription(String description);
}
