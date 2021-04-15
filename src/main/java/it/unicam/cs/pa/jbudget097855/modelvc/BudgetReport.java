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
import java.util.Map;


/**
 * Responsibility: showing the deviations by a specific budget
 * @author LorenzoSerini
 *
 */
public interface BudgetReport {
	/**
	 * Returns tags list
	 * @return tags list
	 */
	List<Tag> tags();
	
	/**
	 * Returns a ledger report
	 * @return ledger report
	 */
	Map<Tag, Double> ledgerReport();
	
	/**
	 * Returns a budget report
	 * @return budget report
	 */
	Map<Tag, Double> expectedReport();
	
	/**
	 * Returns a deviations credit/debit between a expected budget and ledger
	 * @return
	 */
	Map<Tag, Double> report();
	
	Budget getBudget();
	
	/**
	 * Returns the expected value for the tag in ledger
	 * @param t The Tag
	 * @return The tag value in report
	 */
	double getLedgerReport(Tag t);
	
	/**
	 * Returns the expected value for the tag in report
	 * @param t The tag
	 * @return The tag value in report
	 */
	double getReport(Tag t);
	

}
