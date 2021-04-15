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

package it.unicam.cs.pa.jbudget097855;

import static org.junit.jupiter.api.Assertions.assertEquals;





import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.unicam.cs.pa.jbudget097855.jbexception.TransactionException;
import it.unicam.cs.pa.jbudget097855.modelvc.AccountType;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicLedger;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicTag;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicTransaction;
import it.unicam.cs.pa.jbudget097855.modelvc.Ledger;
import it.unicam.cs.pa.jbudget097855.modelvc.MoneyMovement;
import it.unicam.cs.pa.jbudget097855.modelvc.MovementType;
import it.unicam.cs.pa.jbudget097855.modelvc.MyBudget;
import it.unicam.cs.pa.jbudget097855.modelvc.MyBudgetReport;

public class ReportsTest {
	private MoneyMovement expenditure;
	private MoneyMovement interests;
	private BasicTransaction transaction;
	private MyBudget budget;
	private Ledger ledger;
	private MyBudgetReport budgetReport;
	
	@BeforeEach
	void initReportTest() {
		try {
		expenditure = new MoneyMovement(MovementType.DEBIT, "", 500);
		interests = new MoneyMovement(MovementType.DEBIT, "", 2);
		
		expenditure.addTag(BasicTag.SPORT);
		interests.addTag(BasicTag.INTERESTS);
		
		budget = new MyBudget();
		budget.set(BasicTag.SPORT, -100);
		budget.set(BasicTag.TECHNOLOGY, 200);
		budget.set(BasicTag.INTERESTS, -50);
		
		ledger = new BasicLedger();
		ledger.addAccount(AccountType.ASSETS,"Lorenzo Serini", "my Bank Account", 500);
		ledger.addAccount(AccountType.LIABILITIES,"Lorenzo Serini", "my Credit Card", 100);
		
		expenditure.setAccount(ledger.getAccounts().get(0));
		interests.setAccount(ledger.getAccounts().get(1));
		
		transaction = new BasicTransaction();
		transaction.addMovement(expenditure);
		transaction.addMovement(interests);
		
		ledger.addTransaction(transaction);
		
		budgetReport = new MyBudgetReport(ledger, budget);
		} catch (TransactionException e) {
			System.err.println(e.getMessage());
		}
		}
	
	@Test
	public void valueOfReportAreCorrectTest() {
		assertEquals(-400, budgetReport.getReport(BasicTag.SPORT));
		assertEquals(-200, budgetReport.getReport(BasicTag.TECHNOLOGY));
		assertEquals(48, budgetReport.getReport(BasicTag.INTERESTS));
	}

}
