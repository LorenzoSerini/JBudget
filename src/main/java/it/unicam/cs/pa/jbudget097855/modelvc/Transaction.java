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
 * A Transaction is a collection of {@link Movement}.
 * This interface allows you to access and edit transaction informations.
 * @author LorenzoSerini
 *
 */
public interface Transaction {
	/**
	 * Returns transaction ID
	 * @return transaction ID
	 */
	String getId();
	
	/**
	 * Sets a date for the transaction and for all movements that are in it
	 * @param d The movement date
	 */
	void setDate(LocalDate d);
	
	/**
	 * Returns the transaction date
	 * @return transaction date
	 */
	LocalDate getDate();
	
	/**
	 * Adds a tag for the transaction and for all movements that are in it
	 * @param tag The Tag
	 */
	void addTag(Tag tag);
	 /**
	  * Removes a tag for the transaction and for all movements that are in it
	  * @param tag The Tag
	  */
	void removeTag(Tag tag);
	
	/**
	 * Returns tags list
	 * @return tags list
	 */
	List<Tag> getTags();
	
	/**
	 * Returns transaction amount
	 * @return transaction amount
	 */
	double getTotalAmount();
	
	/**
	 * Adds a movement
	 * @param m The Movement
	 */
	void addMovement(Movement m);
	
	/**
	 * Removes a movement
	 * @param m The Movement
	 */
	void removeMovement(Movement m);
	
	/**
	 * Returns a movements list
	 * @return movements list
	 */
	List<Movement> getMovements();
}
