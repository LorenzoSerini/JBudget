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
import java.util.LinkedList;
import java.util.List;

public class ScheduledTransaction implements Scheduled{
	private boolean isCompleted;
	private LinkedList<Transaction> transactions = new LinkedList<>();
	private LocalDate date;
	private String description;
	
	
	public ScheduledTransaction(LocalDate date, String description) {
		this.date=date;
		this.description=description;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	@Override
	public boolean isCompleted() {
		if(LocalDate.now().isBefore(getDate())) {
			this.isCompleted=false;
		}else this.isCompleted=true;
		return isCompleted;
	}

	@Override
	public void setDescription(String description) {
		this.description=description;
	}

	@Override
	public LocalDate getDate() {
		return this.date;
	}

	@Override
	public void addTransaction(Transaction t) {
		if(!this.transactions.contains(t)) {
			t.setDate(getDate());
			this.transactions.add(t);
		}
	}

	@Override
	public void removeTransaction(Transaction t) {
		this.transactions.remove(t);
	}
	
	@Override
	public String toString() {
		return getDate()+" "+getTransactions().toString();
	}

}
