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

/**
 * Allows you to access and edit account informations
 * 
 * @author LorenzoSerini
 *
 */

public interface Account {
	
	/**
	 * Returns account name
	 * @return account name
	 */
	String getName();
	
	/**
	 * Returns account description
	 * @return account description
	 */
	String getDescription();
	
	/**
	 * Returns account ID
	 * @return account ID
	 */
	String getId();
	
	/**
	 * Returns opening Balance
	 * @return opening balance
	 */
	double getOpeningBalance();
	
	/**
	 * Returns the account current balance
	 * @return current balance
	 */
	double getBalance();
	
	/**
	 * Returns the account movements list
	 * @return movements list
	 */
	List<Movement> getMovements();
	
	/**
	 * Returns the list of account movements related to the predicate
	 * @param p The Predicate
	 * @return the movements list that respect the parameter
	 */
	List<Movement> getMovements(Predicate<Movement> p);
	
	/**
	 * Adds a movement
	 * @param m The Movement
	 */
	void addMovement(Movement m);
	
	/**
	 * Removes a specific movement
	 * @param m The Movement
	 */
	void removeMovement(Movement m);
}
