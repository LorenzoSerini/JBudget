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
import java.util.UUID;

public class MoneyMovement implements Movement{
	private final String description;
	private final MovementType type;
	private final double amount;
	private final UUID id = UUID.randomUUID();
	private LocalDate date;
	private final List<Tag> tags = new LinkedList<Tag>();
	private Transaction transaction;
	private Account account;
	
	 public MoneyMovement(MovementType type, String description, double amount) {
		 this.type=type;
		 this.description=description;
		 this.amount=debitOrCredit(type, Math.abs(amount));
		 this.date=LocalDate.now();
	 }

	 
	 private double debitOrCredit(MovementType type, double amount) {
		 switch (type) {
		case CREDIT:
			return amount;
		case DEBIT:
			return -(amount);
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	 }
	 
	 
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public MovementType getType() {
		return this.type;
	}

	@Override
	public double getAmount() {
		return this.amount;
	}
	
	@Override
	public void setTransaction(Transaction t) {
		this.transaction=t;
		this.date=transaction.getDate();
	}

	@Override
	public Transaction getTransaction() {
		return this.transaction;
	}

	@Override
	public void setAccount(Account a) {
		this.account=a;
	}

	@Override
	public Account getAccount() {
		return this.account;
	}

	@Override
	public String getId() {
		return this.id.toString();
	}
	
	@Override
	public void setDate(LocalDate ld) {
		this.date=ld;
	}

	@Override
	public LocalDate getDate() {
		return this.date;
	}

	@Override
	public List<Tag> getTags() {
		return this.tags;
	}

	@Override
	public void addTag(Tag t) {
		if(!tags.contains(t)) this.tags.add(t);
	}

	@Override
	public void removeTag(Tag t) {
		this.tags.remove(t);
	}
	
	@Override
	public String toString() {
		return getDescription()+" "+getTags()+" "+getAmount();
	}
	
}
