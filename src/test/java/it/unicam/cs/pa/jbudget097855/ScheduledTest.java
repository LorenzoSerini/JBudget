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


import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget097855.jbexception.TransactionException;
import it.unicam.cs.pa.jbudget097855.modelvc.AccountType;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicLedger;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicTag;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicTransaction;
import it.unicam.cs.pa.jbudget097855.modelvc.Ledger;
import it.unicam.cs.pa.jbudget097855.modelvc.MoneyMovement;
import it.unicam.cs.pa.jbudget097855.modelvc.MovementType;
import it.unicam.cs.pa.jbudget097855.modelvc.ScheduledTransaction;
import it.unicam.cs.pa.jbudget097855.modelvc.Transaction;


import org.junit.jupiter.api.BeforeEach;

public class ScheduledTest {
	private MoneyMovement expenditure;
	private MoneyMovement interests;
	private MoneyMovement expenditure2;
	private MoneyMovement interests2;
	private Transaction transaction1;
	private Transaction transaction2;
	private ScheduledTransaction scheduled;
	private Ledger ledger;
	
	@BeforeEach
	void initReportTest() {
		expenditure = new MoneyMovement(MovementType.DEBIT, "", 500);
		interests = new MoneyMovement(MovementType.DEBIT, "", 2);
		
		expenditure2 = new MoneyMovement(MovementType.DEBIT, "", 200);
		interests2 = new MoneyMovement(MovementType.DEBIT, "", 20);
		
		expenditure.addTag(BasicTag.SPORT);
		interests.addTag(BasicTag.INTERESTS);
		
		transaction1 = new BasicTransaction();
		transaction2 = new BasicTransaction();
		
		transaction1.addMovement(expenditure);
		transaction1.addMovement(interests);
		
		transaction2.addMovement(expenditure2);
		transaction2.addMovement(interests2);
		
		ledger = new BasicLedger();
		ledger.addAccount(AccountType.ASSETS,"Lorenzo Serini", "my Bank Account", 500);
		ledger.addAccount(AccountType.LIABILITIES,"Lorenzo Serini", "my Credit Card", 100);
		
		expenditure.setAccount(ledger.getAccounts().get(0));
		interests.setAccount(ledger.getAccounts().get(0));
		
		expenditure2.setAccount(ledger.getAccounts().get(1));
		interests2.setAccount(ledger.getAccounts().get(1));
		
		scheduled = new ScheduledTransaction(LocalDate.now(), "");
		scheduled.addTransaction(transaction1);
		scheduled.addTransaction(transaction2);
		
		ledger.addScheduledTransaction(scheduled);
	}
	
	@Test
	void scheduleTransactionsInLedger() {
		try {
			ledger.schedule();
			assertEquals(ledger.getTransactions().get(0), transaction1);
			assertEquals(ledger.getTransactions().get(1), transaction2);
			assertEquals(0, ledger.getSchedulers().size());
		} catch (TransactionException e) {
			e.printStackTrace();
		}
	}

}
