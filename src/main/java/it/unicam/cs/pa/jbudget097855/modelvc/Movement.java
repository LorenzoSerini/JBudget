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
 * Allows you to access and edit movement informations.
 * 
 * @author LorenzoSerini
 *
 */

public interface Movement{
	
	
	
	/**
	 * Returns movement description
	 * @return movement description
	 */
	String getDescription();
	
	/**
	 * Returns movement type
	 * @return MovementType
	 */
	MovementType getType();
	
	/**
	 * Returns movement amount
	 * @return movement amount
	 */
	double getAmount();
	
	/**
	 * Sets the transaction associated with movement
	 * @param t The transaction
	 */
	void setTransaction(Transaction t);
	
	/**
	 * Return the transaction associated with movement
	 * @return Transaction
	 */
	Transaction getTransaction();
	
	/**
	 * Sets the account associated with movement
	 * @param a
	 */
	void setAccount(Account a);
	
	/**
	 * Returns the account associated with movement
	 * @return Account
	 */
	Account getAccount();
	
	/**
	 * Returns movement ID
	 * @return movement ID
	 */
	String getId();
	
	/**
	 * Sets the movement date
	 * @param ld
	 */
	void setDate(LocalDate ld);
	
	/**
	 * Returns the movement date
	 * @return
	 */
	LocalDate getDate();
	
	/**
	 * Returns tags list
	 * @return tags list
	 */
	List<Tag> getTags();
	
	/**
	 * Adds a tag
	 * @param t The tag
	 */
	void addTag(Tag t);
	
	/**
	 * Removes a specific tag
	 * @param t The tag
	 */
	void removeTag(Tag t);
	
}
