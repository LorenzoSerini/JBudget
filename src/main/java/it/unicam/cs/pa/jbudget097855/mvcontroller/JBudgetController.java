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


import java.io.IOException;




import java.time.LocalDate;
import java.util.Stack;
import java.util.logging.Logger;

import it.unicam.cs.pa.jbudget097855.jbexception.DateException;
import it.unicam.cs.pa.jbudget097855.jbexception.OpeningBalanceException;
import it.unicam.cs.pa.jbudget097855.jbexception.TransactionException;
import it.unicam.cs.pa.jbudget097855.modelvc.AccountType;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicLedger;
import it.unicam.cs.pa.jbudget097855.modelvc.BasicTransaction;
import it.unicam.cs.pa.jbudget097855.modelvc.Budget;
import it.unicam.cs.pa.jbudget097855.modelvc.MoneyMovement;
import it.unicam.cs.pa.jbudget097855.modelvc.Movement;
import it.unicam.cs.pa.jbudget097855.modelvc.MovementType;
import it.unicam.cs.pa.jbudget097855.modelvc.MyBudget;
import it.unicam.cs.pa.jbudget097855.modelvc.PersonalBudgetManager;
import it.unicam.cs.pa.jbudget097855.modelvc.ScheduledTransaction;
import it.unicam.cs.pa.jbudget097855.modelvc.Tag;
import it.unicam.cs.pa.jbudget097855.modelvc.Transaction;
import it.unicam.cs.pa.jbudget097855.persistence.LedgerPersistenceManager;



public class JBudgetController implements Controller<BasicLedger, PersonalBudgetManager>{
	private BasicLedger ledger;
	private PersonalBudgetManager budgetManager;
	private Stack<Movement> movements = new Stack<>();
	private MyBudget myBudget;
	private LedgerPersistenceManager persistence;
	private Logger logger = Logger.getLogger("it.unicam.cs.pa.jbudget097855.mvcontroller.Controller");


	public JBudgetController(BasicLedger ledger, PersonalBudgetManager budgetManager) {
			this.ledger=ledger;
			this.budgetManager=budgetManager;
			this.myBudget = new MyBudget();
			this.persistence = new LedgerPersistenceManager();
	}

	@Override
	public void processTag(String name, String description) {
		this.ledger.addTag(name, description);
		logger.finest("Tag created");
	}

	
	public void deleteTag(Tag tag) {
		this.ledger.removeTag(tag);
		logger.finest("Tag removed");
	}


	@Override
	public void processAccount(String type, String name, String description, double openingBalance) throws OpeningBalanceException{
		if(openingBalance>=0) {
			this.ledger.addAccount(AccountType.valueOf(type.toUpperCase()), name, description, openingBalance);
			logger.finest("Account created");
		}else throw new OpeningBalanceException(openingBalance);
	}

	@Override
	public void processMovement(int accountIndex, String type, String description, double amount, int...tagIndex) {
		MoneyMovement m = new MoneyMovement(MovementType.valueOf(type.toUpperCase()), description, amount);
		for(int i:tagIndex) m.addTag(this.ledger.getTags().get(i));
		m.setAccount(this.ledger.getAccounts().get(accountIndex));
		this.movements.add(m);
		logger.finest("Movement created");
	}

	public void addTagInMovement(int movementIndex, int tagIndex) {
		this.movements.get(movementIndex).addTag(this.ledger.getTags().get(tagIndex));
	}



	@Override
	public void processTransactionWithMovements(int...tagIndex) throws TransactionException {	
		BasicTransaction t  = new BasicTransaction();
		for(Movement m:movements) t.addMovement(m);
		for(int i:tagIndex) t.addTag(this.ledger.getTags().get(i));
		this.ledger.addTransaction(t);
		clearMovementsStack();
		logger.finest("Transaction created");
	}

	public void deleteMovement(int index) {
		this.movements.remove(index);
		logger.finest("Movement deleted");
	}

	public void clearMovementsStack() {
		this.movements.clear();
	}

	public Stack<Movement> getMovementsStack(){
		return this.movements;
	}

	public void setBudgetDescription(String description) {
		this.myBudget.setDescription(description);
	}

	public Budget getBudget() {
		return this.myBudget;
	}

	@Override
	public void setExpectedBudget(int tagIndex, double expectedValue) {
		this.myBudget.set(this.ledger.getTags().get(tagIndex), expectedValue);
	}

	@Override
	public void generateReport() {
		if(!this.myBudget.tags().isEmpty()) {
			this.budgetManager.generateReport(this.ledger, this.myBudget);
			this.myBudget = new MyBudget();
			logger.finest("Report generated");
		}
	}

	@Override
	public void addScheduledTransaction(LocalDate date, String description, int...transactionIndex) throws DateException {
		if(LocalDate.now().isBefore(date)){
			ScheduledTransaction scheduled = new ScheduledTransaction(date, description);
			for(int i:transactionIndex) {
				scheduled.addTransaction(cloneTransaction(i));
			}
			this.ledger.addScheduledTransaction(scheduled);
			logger.finest("Scheduled created");
		}else throw new DateException(date);
	}


	public void addTransactionInScheduler(int schedulerIndex, int transactionIndex){
		this.ledger.getSchedulers().get(schedulerIndex).addTransaction(cloneTransaction(transactionIndex));
	}


	private Transaction cloneTransaction(int indexTransaction) {
		BasicTransaction transaction = new BasicTransaction();
		for(Movement m:this.ledger.getTransactions().get(indexTransaction).getMovements()) {
			MoneyMovement mov = new MoneyMovement(m.getType(), m.getDescription(), m.getAmount());
			mov.setAccount(m.getAccount());
			for(Tag t:m.getTags())mov.addTag(t);
			transaction.addMovement(mov);
		}
		return transaction;
	}


	@Override
	public void scheduleTransactions() throws TransactionException {
		this.ledger.schedule();
		logger.finest("Schedule");
	}
	
	/**
	 * saves this ledger data
	 * @throws IOException
	 */
	public void save() throws IOException {
		persistence.save(this.ledger);
		logger.info("Application saved");
	}
	
	/**
	 * Loads this ledger data
	 * @throws IOException
	 */
	public void load() throws IOException {
		this.ledger = persistence.load();
		logger.info("Application loaded");
	}
	
	@Override
	public BasicLedger getLedger() {
		return this.ledger;
	}
	
	@Override
	public PersonalBudgetManager getBudgetManager() {
		return this.budgetManager;
	}
	
}
	
