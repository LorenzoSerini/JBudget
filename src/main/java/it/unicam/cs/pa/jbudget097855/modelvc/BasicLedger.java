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

import java.util.LinkedList;




import java.util.List;
import java.util.function.Predicate;

import it.unicam.cs.pa.jbudget097855.jbexception.TransactionException;


public class BasicLedger implements Ledger{
	private final LinkedList<Account> accounts = new LinkedList<>();
	private final LinkedList<Transaction> transactions = new LinkedList<>();
	private final LinkedList<Tag> tags = new LinkedList<>();
	private final LinkedList<Scheduled> schedulers = new LinkedList<>();
	
	
	@Override
	public List<Account> getAccounts() {
		return this.accounts;
	}

	@Override
	public void addTransaction(Transaction t) throws TransactionException {
		if(!t.getMovements().isEmpty()) {
		for(Movement m:t.getMovements()) {
			if(m.getAccount()!=null) {
				m.getAccount().addMovement(m);
			}
			else throw new TransactionException(m.getDescription()+" movement has no associated account");
		}
		 this.transactions.add(t);
		}else throw new TransactionException("Transaction movements list is empty");
	}

	@Override
	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	@Override
	public List<Transaction> getTransactions(Predicate<Transaction> p) {
		LinkedList<Transaction> subMovimentsList = new LinkedList<>();
		transactions.stream()
		.filter(p)
		.forEach(subMovimentsList::add);
	 	return subMovimentsList;
	}
	
	@Override
	public Tag addTag(String name, String description) {
			this.tags.add(new BasicTag(name, description));
			return this.tags.getLast();
	}

	@Override
	public List<Tag> getTags() {
		return this.tags;
	}

	@Override
	public Account addAccount(AccountType type, String name, String description, double openingBalance) {
		switch (type) {
		case ASSETS:
			this.accounts.add(new Assets(name, description, openingBalance));
			return this.accounts.getLast();
		case LIABILITIES:
			this.accounts.add(new Liabilities(name, description, openingBalance));
			return this.accounts.getLast();
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	@Override
	public void removeTag(Tag t) {
		this.tags.remove(t);	
		}

	@Override
	public void addScheduledTransaction(ScheduledTransaction s) {
		this.schedulers.add(s);
	}

	@Override
	public List<Scheduled> getSchedulers() {
		return this.schedulers;
	}

	@Override
	public void schedule() throws TransactionException {
		for(Scheduled s:this.schedulers) {
			if(s.isCompleted()) {
				for(Transaction t:s.getTransactions()) addTransaction(t);
				this.schedulers.remove(s);
			}
		}
	}
}
